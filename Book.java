import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * pojo class to import book details
 */
class Book {

    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private Double bookPrice;
    private int totalSaleCount;

    public Book() {
    }

    public Book(String bookId, String bookTitle, String bookAuthor, Double bookPrice) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getTotalSaleCount() {
        return totalSaleCount;
    }

    public void setTotalSaleCount(int totalSaleCount) {
        this.totalSaleCount = totalSaleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return totalSaleCount == book.totalSaleCount &&
                Objects.equals(bookId, book.bookId) &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(bookAuthor, book.bookAuthor) &&
                Objects.equals(bookPrice, book.bookPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, bookAuthor, bookPrice, totalSaleCount);
    }
}

/**
 * pojo class to import sales details
 */
class Sale {
    private LocalDate saleDate;
    private String saleEmail;
    private PaymentMode salePaymentMethod;
    private Integer saleItemCount;
    private Double salePrice = 0.0;
    private Map<String, Integer> purchasedBookQuantity = new HashMap<>();

    public Sale() {
    }

    public Sale(LocalDate saleDate, String saleEmail, PaymentMode salePaymentMethod, Integer saleItemCount) {
        this.saleDate = saleDate;
        this.saleEmail = saleEmail;
        this.salePaymentMethod = salePaymentMethod;
        this.saleItemCount = saleItemCount;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleEmail() {
        return saleEmail;
    }

    public void setSaleEmail(String saleEmail) {
        this.saleEmail = saleEmail;
    }

    public PaymentMode getSalePaymentMethod() {
        return salePaymentMethod;
    }

    public void setSalePaymentMethod(PaymentMode salePaymentMethod) {
        this.salePaymentMethod = salePaymentMethod;
    }

    public Integer getSaleItemCount() {
        return saleItemCount;
    }

    public void setSaleItemCount(Integer saleItemCount) {
        this.saleItemCount = saleItemCount;
    }

    public Map<String, Integer> getPurchasedBookQuantity() {
        return purchasedBookQuantity;
    }

    public void setPurchasedBookQuantity(Map<String, Integer> purchasedBookQuantity) {
        this.purchasedBookQuantity = purchasedBookQuantity;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(saleDate, sale.saleDate) &&
                Objects.equals(saleEmail, sale.saleEmail) &&
                salePaymentMethod == sale.salePaymentMethod &&
                Objects.equals(saleItemCount, sale.saleItemCount) &&
                Objects.equals(salePrice, sale.salePrice) &&
                Objects.equals(purchasedBookQuantity, sale.purchasedBookQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleDate, saleEmail, salePaymentMethod, saleItemCount, salePrice, purchasedBookQuantity);
    }
}

/**
 * Enum for payment mode
 */
enum PaymentMode {
    CASH, CARD
}
