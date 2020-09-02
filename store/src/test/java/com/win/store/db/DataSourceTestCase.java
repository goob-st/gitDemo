package com.win.store.db;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTestCase {
    @Autowired
    DataSource dataSource;

    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
