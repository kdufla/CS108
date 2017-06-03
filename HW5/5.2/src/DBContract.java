/**
 * Created by kdufla on 5/28/17.
 */
public class DBContract {

    public static class productTable {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_PRODUCTID = "productid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IMAGE = "imagefile";
        public static final String COLUMN_NAME_PRICE = "price";
    }

    public static class dataManip{
        public static final String SELECT=" SELECT ";
        public static final String FROM=" FROM ";
        public static final String INSERT=" INSERT ";
        public static final String INTO=" INTO ";
        public static final String WHERE=" WHERE ";
        public static final String VALUES=" VALUES ";
        public static final String ORD=" ORDER BY ";
        public static final String LIKE=" LIKE ";
        public static final String AND=" AND ";
    }
}
