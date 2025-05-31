package com.wplab.homework3.core;

import java.sql.*;

public abstract class BaseRepository {
    private Connection conn;

    public void setConnection(Connection connection) {
        this.conn = connection;
        if (conn != null) {
            System.out.println("ExampleRepositoryImpl received connection: " + connection);
        } else {
            System.out.println("ExampleRepositoryImpl connection cleared.");
        }
    }

    private static void setParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Object param = params[i];
                int jdbcIndex = i + 1; // JDBC 파라미터 인덱스는 1부터 시작

                if (param == null) {
                    pstmt.setObject(jdbcIndex, null);
                } else if (param instanceof String) {
                    pstmt.setString(jdbcIndex, (String) param);
                } else if (param instanceof Integer) {
                    pstmt.setInt(jdbcIndex, (Integer) param);
                } else if (param instanceof Long) {
                    pstmt.setLong(jdbcIndex, (Long) param);
                } else if (param instanceof Double) {
                    pstmt.setDouble(jdbcIndex, (Double) param);
                } else if (param instanceof Float) {
                    pstmt.setFloat(jdbcIndex, (Float) param);
                } else if (param instanceof Boolean) {
                    pstmt.setBoolean(jdbcIndex, (Boolean) param);
                } else if (param instanceof java.sql.Date) {
                    pstmt.setDate(jdbcIndex, (java.sql.Date) param);
                } else if (param instanceof java.sql.Time) {
                    pstmt.setTime(jdbcIndex, (java.sql.Time) param);
                } else if (param instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(jdbcIndex, (java.sql.Timestamp) param);
                } else if (param instanceof java.util.Date) {
                    // java.util.Date는 java.sql.Timestamp로 변환
                    pstmt.setTimestamp(jdbcIndex, new Timestamp(((java.util.Date) param).getTime()));
                } else {
                    // 기타 타입은 setObject로 처리 (JDBC 드라이버가 변환 시도)
                    pstmt.setObject(jdbcIndex, param);
                }
            }
        }
    }

    protected void executeCommand(String sql, Object... params) throws SQLException {
        try (var pstmt = conn.prepareStatement(sql)) {
            setParameters(pstmt, params);
            pstmt.executeUpdate();
        }
    }

    protected QueryResult executeQuery(String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            return new QueryResult(pstmt, rs); // pstmt와 rs를 함께 반환
        } catch (SQLException e) {
            // 예외 발생 시, 생성된 리소스가 있다면 닫아줘야 함
            if (rs != null) try { rs.close(); } catch (SQLException suppressed) { e.addSuppressed(suppressed); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException suppressed) { e.addSuppressed(suppressed); }
            throw e;
        }
    }
}
