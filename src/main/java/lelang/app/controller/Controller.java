package lelang.app.controller;

import java.util.Map;

public  abstract class Controller {
    
    public abstract void getData();

    public abstract <T> void createData(Map<String, Object> request, T entity);

    public abstract <T> void updateData(Map<String, Object> request, T entity);

    public abstract void deleteData(long id);
}
