package models;

import java.util.HashMap;

public class Dataset
{
    private HashMap<String, String> map;
    private String name;

    public Dataset(String name, HashMap<String, String> map)
    {
        super();
        this.map = map;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public String getValue(String key)
    {
        return this.map.get(key);
    }
}
