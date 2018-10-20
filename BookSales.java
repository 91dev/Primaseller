import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BookSales {
    private static Map<String, Book> stringBookMap;
    private static List<Sale> salesList;
    private static DecimalFormat df2 = new DecimalFormat("##.##");// to precise decimal value up to 2 place

    public static void main(String[] args) throws Exception {
        if (args.length < 2 || (!args[0].startsWith("--books=") && !args[1].startsWith("--sales="))){
            throw new Exception("Atleast two args are required to run the programme! First args should be --books=/path/to/books.list and Second args should be --sales=/path/to/sales.list");
        }
        processBookFile(args[0].split("=")[1]);
        processSalesFile(args[1].split("=")[1]);
        if (args.length > 2){
            for (int t=2; t < args.length; t++){
                if (args[t].startsWith("--top_selling_books=")){
                    printTopSellingBooks(Integer.parseInt(args[t].split("=")[1]));
                } else if (args[t].startsWith("--top_customers=")){
                    printTopCustomers(Integer.parseInt(args[t].split("=")[1]));
                } else if (args[t].startsWith("--sales_on_date=")){
                    printSalesOnDate(LocalDate.parse(args[t].split("=")[1]));
                }
            }
        }
    }

    /**
     *
     * @param bookFilePath : csv file of book list
     * @return Map<String, Book> key as bookId and value as Book
     */
    private static Map<String, Book> processBookFile(String bookFilePath) {
        Map<String, Book> bookIdBookMap = new HashMap<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(bookFilePath))));
            br.lines().forEach(ln ->{
                    String[] p = ln.split(",");// a CSV has comma separated lines
                    if (p.length > 3) {
                        bookIdBookMap.put(p[0], new Book(p[0], p[1], p[2], Double.parseDouble(p[3])));
                    }
            });
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stringBookMap = bookIdBookMap;
        return bookIdBookMap ;
    }

    /**
     *
     * @param saleFilePath : csv file path of sale list
     * @return List of Sale
     */
    private static List<Sale> processSalesFile(String saleFilePath){
        List<Sale> saleList = new ArrayList<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(saleFilePath))));
            br.lines().forEach(ln ->{
                String[] s = ln.split(",");// a CSV has comma separated lines
                Sale sale = null;
                if (s.length > 4) {
                    sale = new Sale(LocalDate.parse(s[0]), s[1], "CASH".equals(s[2]) ? PaymentMode.CASH : PaymentMode.CARD, Integer.parseInt(s[3]));
                    int bound = s.length;
                    for (int d = 4; d < bound; d++) {
                        String[] q = s[d].split(";");
                        sale.getPurchasedBookQuantity().put(q[0], Integer.parseInt(q[1]));
                        // adding total sales count in Book
                        if (stringBookMap.containsKey(q[0])){
                            stringBookMap.get(q[0]).setTotalSaleCount(stringBookMap.get(q[0]).getTotalSaleCount() + Integer.parseInt(q[1]));
                            sale.setSalePrice(sale.getSalePrice() + stringBookMap.get(q[0]).getBookPrice()*Integer.parseInt(q[1]));
                        }
                    }
                    saleList.add(sale);
                }
            });
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        salesList = saleList;
        return saleList;
    }

    /**
     *
     * @param top: to print number of top selling books based on total price (totalSaleCount*bookPrice)
     */
    public static void printTopSellingBooks(int top){
        Map<String, Double> topSellingMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, Book> entry : stringBookMap.entrySet()) {
            topSellingMap.put(entry.getKey(), entry.getValue().getBookPrice()*entry.getValue().getTotalSaleCount());
        }
        int i = 1;
        System.out.print("top_selling_books" + "\t");
        for (String entry : topSellingMap.keySet()) {
            if (i > top) {
                break;
            }
            System.out.print(entry + "\t");
            i++;
        }
        System.out.println();
    }

    /**
     *
     * @param date: passing the specific date
     *            to print total sales price made on specific date
     */
    public static void printSalesOnDate(LocalDate date){
        Double price = 0.0;
        for (Sale sale : salesList) {
            if (sale.getSaleDate().equals(date)){
                price += sale.getSalePrice();
            }
        }
        System.out.print("sales_on_date" + "\t");
        System.out.print(date.toString() + "\t");
        System.out.println(df2.format(price));
    }

    /**
     *
     * @param top: to print number of top customer based on total amount spend for sale
     */
    private static void printTopCustomers(int top){
        //preparing the data with customer email id as key and total price of purchase made as value
        Map<String, Double> customerMap = new TreeMap<>(Collections.reverseOrder());
        for (Sale sale : salesList) {
            if (customerMap.containsKey(sale.getSaleEmail())){
                customerMap.put(sale.getSaleEmail(), customerMap.get(sale.getSaleEmail()) + sale.getSalePrice());
            } else {
                customerMap.put(sale.getSaleEmail(), sale.getSalePrice());
            }
        }
        int i = 1;
        System.out.print("top_customers" + "\t");
        for (String email : customerMap.keySet()) {
            if (i > top) {
                break;
            }
            System.out.print(email + "\t");
            i++;
        }
        System.out.println();
    }
}

