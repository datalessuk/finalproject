package mark.harrison.finalproject;

public class beerstwo {

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return Brewery;
    }

    public void setBrewery(String brewery) {
        Brewery = brewery;
    }

    private String barcode;
    private String name;
    private String Brewery;

    public beerstwo(){

    }

    public beerstwo(String barcode, String name, String brewery) {
        this.barcode = barcode;
        this.name = name;
        this.Brewery = brewery;
    }
}
