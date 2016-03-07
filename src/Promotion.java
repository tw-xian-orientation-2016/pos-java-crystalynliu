import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private String type;
    private String[] barcode;

    public Promotion(String type,String[] barcode){
        this.type = type;
        this.barcode = barcode;
    }

    public void setBarcode(String[] barcode) {
        this.barcode = barcode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String[] getBarcode() {
        return barcode;
    }

    public static List<Promotion> loadPromotions(){
        List<Promotion> promotions = new ArrayList<Promotion>();
        String[] barcodes = {"ITEM000000","ITEM000001","ITEM000005"};
        Promotion promotion = new Promotion("BUY_TWO_GET_ONE_FREE",barcodes);
        promotions.add(promotion);
        return promotions;
    }
}
