package com.carbooking.tp;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSource extends DriverManagerDataSource{
	
	private static DataSource dataSource;
	
	public static final String DRIVER = "org.h2.Driver";
    public static final String JDBC_URL = "jdbc:h2:file:~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
    
    public static DataSource getDataSource(){
    	
        dataSource = new DataSource();
        
        dataSource.setDriverClassName(DataSource.DRIVER);
        dataSource.setUrl(DataSource.JDBC_URL);
        dataSource.setUsername(DataSource.USERNAME);
        dataSource.setPassword(DataSource.PASSWORD);
        return dataSource;
    }
}
