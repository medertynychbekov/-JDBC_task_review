package org.peaksoft.service;


import org.peaksoft.model.Car;
import org.peaksoft.util.Util;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements Service<Car> {

    public void createTable() {
        String query = """
                CREATE TABLE cars
                (
                    id              SERIAL PRIMARY KEY,
                    model           VARCHAR NOT NULL,
                    year_of_release TIMESTAMP,
                    color           VARCHAR NOT NULL
                );
                """;

        try (Connection connection = Util.connection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Наша таблица CARS успешно создан!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String query = """
                DROP TABLE cars;
                """;
        try (Connection connection = Util.connection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("table CARS successfully dropped!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(Car car) {
        String query = """
                INSERT INTO cars(model, year_of_release, color)
                VALUES (?, ?, ?);
                """;

        try (Connection connection = Util.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, car.getModel());
            ps.setTimestamp(2, Timestamp.valueOf(car.getYearOfRelease().atStartOfDay()));
            ps.setString(3, car.getColor());
            ps.execute();
            System.out.println("Car entity successfully saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeById(long id) {
        String query = "DELETE FROM cars WHERE id = ?;";
        try (Connection connection = Util.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (int) id);
            ps.execute();
            System.out.println("Car with id: " + id + " successfully deleted!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Car> getAll() {
        String SQL = "SELECT * FROM cars";

        List<Car> cars = new ArrayList<>();
        try (Connection connection = Util.connection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Car car = new Car(
                        (long) resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getDate("year_of_release").toLocalDate(),
                        resultSet.getString("color")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }

    public void cleanTable() {
        String query = "TRUNCATE TABLE cars;";
        try (Connection connection = Util.connection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("table CARS successfully cleaned!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Car getById(long id) {
        String SQL = "SELECT * FROM cars WHERE id = ?";
        Car car = null;
        try (Connection connection = Util.connection()) {
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                car = new Car(
                        (long) resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getDate("year_of_release").toLocalDate(),
                        resultSet.getString("color")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return car;
    }
}
