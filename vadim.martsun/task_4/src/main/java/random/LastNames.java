package random;

public enum LastNames {
    SCOFIELD("Scofield"),
    MARTSUN("Martsun"),
    GROSMAN("Grosman"),
    COOPER("Cooper"),
    SMITH("Smith");

    private String name;

    LastNames(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
