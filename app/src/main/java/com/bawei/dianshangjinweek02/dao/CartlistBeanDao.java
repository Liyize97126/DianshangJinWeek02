package com.bawei.dianshangjinweek02.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.dianshangjinweek02.bean.CartlistBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CARTLIST_BEAN".
*/
public class CartlistBeanDao extends AbstractDao<CartlistBean, Long> {

    public static final String TABLENAME = "CARTLIST_BEAN";

    /**
     * Properties of entity CartlistBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property ShopId = new Property(1, long.class, "shopId", false, "SHOP_ID");
        public final static Property ShopName = new Property(2, String.class, "shopName", false, "SHOP_NAME");
        public final static Property DefaultPic = new Property(3, String.class, "defaultPic", false, "DEFAULT_PIC");
        public final static Property ProductId = new Property(4, long.class, "productId", false, "PRODUCT_ID");
        public final static Property ProductName = new Property(5, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property Color = new Property(6, String.class, "color", false, "COLOR");
        public final static Property Meal = new Property(7, String.class, "meal", false, "MEAL");
        public final static Property Price = new Property(8, long.class, "price", false, "PRICE");
        public final static Property Count = new Property(9, int.class, "count", false, "COUNT");
        public final static Property Checked = new Property(10, boolean.class, "checked", false, "CHECKED");
    }


    public CartlistBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CartlistBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CARTLIST_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"SHOP_ID\" INTEGER NOT NULL ," + // 1: shopId
                "\"SHOP_NAME\" TEXT," + // 2: shopName
                "\"DEFAULT_PIC\" TEXT," + // 3: defaultPic
                "\"PRODUCT_ID\" INTEGER NOT NULL ," + // 4: productId
                "\"PRODUCT_NAME\" TEXT," + // 5: productName
                "\"COLOR\" TEXT," + // 6: color
                "\"MEAL\" TEXT," + // 7: meal
                "\"PRICE\" INTEGER NOT NULL ," + // 8: price
                "\"COUNT\" INTEGER NOT NULL ," + // 9: count
                "\"CHECKED\" INTEGER NOT NULL );"); // 10: checked
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CARTLIST_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CartlistBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getShopId());
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(3, shopName);
        }
 
        String defaultPic = entity.getDefaultPic();
        if (defaultPic != null) {
            stmt.bindString(4, defaultPic);
        }
        stmt.bindLong(5, entity.getProductId());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(6, productName);
        }
 
        String color = entity.getColor();
        if (color != null) {
            stmt.bindString(7, color);
        }
 
        String meal = entity.getMeal();
        if (meal != null) {
            stmt.bindString(8, meal);
        }
        stmt.bindLong(9, entity.getPrice());
        stmt.bindLong(10, entity.getCount());
        stmt.bindLong(11, entity.getChecked() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CartlistBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getShopId());
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(3, shopName);
        }
 
        String defaultPic = entity.getDefaultPic();
        if (defaultPic != null) {
            stmt.bindString(4, defaultPic);
        }
        stmt.bindLong(5, entity.getProductId());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(6, productName);
        }
 
        String color = entity.getColor();
        if (color != null) {
            stmt.bindString(7, color);
        }
 
        String meal = entity.getMeal();
        if (meal != null) {
            stmt.bindString(8, meal);
        }
        stmt.bindLong(9, entity.getPrice());
        stmt.bindLong(10, entity.getCount());
        stmt.bindLong(11, entity.getChecked() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public CartlistBean readEntity(Cursor cursor, int offset) {
        CartlistBean entity = new CartlistBean( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // shopId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // shopName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // defaultPic
            cursor.getLong(offset + 4), // productId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // productName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // color
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // meal
            cursor.getLong(offset + 8), // price
            cursor.getInt(offset + 9), // count
            cursor.getShort(offset + 10) != 0 // checked
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CartlistBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setShopId(cursor.getLong(offset + 1));
        entity.setShopName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDefaultPic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProductId(cursor.getLong(offset + 4));
        entity.setProductName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setColor(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMeal(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPrice(cursor.getLong(offset + 8));
        entity.setCount(cursor.getInt(offset + 9));
        entity.setChecked(cursor.getShort(offset + 10) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CartlistBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CartlistBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CartlistBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}