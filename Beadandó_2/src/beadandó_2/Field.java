package beadandó_2;

public class Field {
    private String value;
    
    public Field(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
}
