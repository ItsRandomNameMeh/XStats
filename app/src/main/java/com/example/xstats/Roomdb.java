//package com.example.xstats;
//
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.Index;
//import androidx.room.Dao;
//import androidx.room.PrimaryKey;
//import androidx.room.Database;
//import androidx.room.RoomDatabase;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//
//
//import java.time.Year;
//
//@Entity(tableName = "cost")
//public class Cost {
//
//    @PrimaryKey
//    public int id;
//
//    @ColumnInfo(name = "Month")
//    public String month;
//
//    @ColumnInfo(name = "Year")
//    public String year;
//
//
//    public Cost(int id, String Month, String Year) {
//        this.id = id;
//        this.month = Month;
//        this.year = Year;
//    }
//
//    // Дополнительные методы, конструкторы и геттеры/сеттеры можно добавить здесь
//}
//
//@Entity(tableName = "car")
//public class Car {
//
//    @PrimaryKey
//    public int id_car;
//
//    @ColumnInfo(name = "Model")
//    public String model;
//
//    @ColumnInfo(name = "Mark")
//    public String mark;
//
//    @ColumnInfo(name = "Year")
//    public String year;
//
//    @ColumnInfo(name = "VIN")
//    public String vin;
//
//    @ColumnInfo(name = "Gnumb")
//    public String gnumb;
//
//    public Car(int id, String Model, String Year, String Mark, String VIN, String Gnumb) {
//        this.id_car = id;
//        this.year = Year;
//        this.model = Model;
//        this.mark = Mark;
//        this.vin = VIN;
//        this.gnumb = Gnumb;
//    }
//
//    // Дополнительные методы, конструкторы и геттеры/сеттеры можно добавить здесь
//}
//@Entity(tableName = "user")
//public class User {
//
//    @PrimaryKey
//    public int id_user;
//
//    @ColumnInfo(name = "Username")
//    public String username;
//
//    @ColumnInfo(name = "Pass")
//    public String pass;
//
//
//    public User(int id_User, String Username, String Pass) {
//        this.id_user = id_User;
//        this.username = Username;
//        this.pass = Pass;
//    }
//
//    // Дополнительные методы, конструкторы и геттеры/сеттеры можно добавить здесь
//}
//
//@Entity(
//        tableName = "maintable",
//        indices = {@Index("id")},
//        foreignKeys = {
//                @ForeignKey(
//                        entity = Cost.class,
//                        parentColumns = {"id"},
//                        childColumns = {"id_cost"}
//                ),
//                @ForeignKey(
//                        entity = User.class,
//                        parentColumns = {"id_user"},
//                        childColumns = {"id_user"}
//                ),
//                @ForeignKey(
//                        entity = Car.class,
//                        parentColumns = {"id_car"},
//                        childColumns = {"id_car"}
//                )
//        }
//)
//
//public class maintable {
//
//    @PrimaryKey(autoGenerate = true)
//    public long id_acc;
//
//    @ColumnInfo(name = "cost_id")
//    public long id_cost;
//
//    @ColumnInfo(name = "user_id")
//    public long id_user;
//
//    @ColumnInfo(name = "car_id")
//    public long id_car;
//
//    public String nextTO_date;
//    public int sum_gas;
//
//    public maintable(long id, long id_car,long id_cost, long id_user, String nextTO_date, int sum_gas) {
//        this.id_acc = id;
//        this.id_cost = id_cost;
//        this.id_user = id_user;
//        this.id_car = id_car;
//        this.nextTO_date = nextTO_date;
//        this.sum_gas = sum_gas;
//    }
//
//    // Дополнительные методы, конструкторы и геттеры/сеттеры можно добавить здесь
//}
//
//@Dao
//public interface StatisticDao {
//    @Insert(entity = maintable.class)
//    void insertNewStatisticData(maintable statistic);
//
//    @Query("SELECT maintable.id_acc, month, year, username,pass, model, mark, year, vin, gnumb, nextTO_date, sum_gas FROM maintable\n" +
//            "INNER JOIN cost ON maintable.cost_id = cost.id\n" +
//            "INNER JOIN user ON maintable.user_id = user.id_user\n"+
//            "INNER JOIN car ON maintable.car_id = car.id_car;")
//    //List<StatisticInfoTuple> getAllStatisticData();
//
//    @Query("DELETE FROM maintable WHERE id_acc = :statisticId")
//    void deleteStatisticDataById(long statisticId);
//}
//
//
//@Database(version = 1, entities = {Cost.class, Car.class, User.class, maintable.class})
//public abstract class AppDatabase extends RoomDatabase {
//
//    public abstract StatisticDao getStatisticDao();
//
//}
//
//
//public class Maintable {
//    private final long id_cost;
//    private final long id_car;
//    private final long id_user;
//    private final String  NextTO_date;
//    private final int sum_gas;
//
//    public Maintable(long id_user, long id_car, long id_cost,String  NextTO_date, int sum_gas) {
//        this.id_car = id_car;
//        this.id_cost = id_cost;
//        this.id_user = id_user;
//        this.NextTO_date = NextTO_date;
//        this.sum_gas = sum_gas;
//    }
//
//    public maintable toStatisticDbEntity() {
//        return new maintable(
//                0,
//                id_car,
//                id_cost,
//                id_user,
//                NextTO_date,
//                sum_gas
//        );
//    }
//}
//
//public class StatisticRepository {
//    private StatisticDao statisticDao;
//
//    public StatisticRepository(StatisticDao statisticDao) {
//        this.statisticDao = statisticDao;
//    }
//
//    public void insertNewStatisticData(maintable statisticDbEntity) {
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                statisticDao.insertNewStatisticData(statisticDbEntity);
//            }
//        });
//    }
//
//    public List<StatisticInfoTuple> getAllStatisticData() {
//        return statisticDao.getAllStatisticData();
//    }
//
//    public void removeStatisticDataById(long id) {
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                statisticDao.deleteStatisticDataById(id);
//            }
//        });
//    }
//}
//
