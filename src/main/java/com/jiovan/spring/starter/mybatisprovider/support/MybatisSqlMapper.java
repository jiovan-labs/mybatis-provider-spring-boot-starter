/*
 * Mybatis SqlMapper of Mybatis_Utils
 * @author abel533
 *
 * https://gitee.com/free/Mybatis_Utils/tree/master/SqlMapper
 */


package com.jiovan.spring.starter.mybatisprovider.support;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;


/**
 * Mybatis SqlMapper
 *
 * @author abel533
 * @see "https://blog.csdn.net/isea533/article/details/40044417"
 * @see "https://blog.csdn.net/dalinsi/article/details/51919160"
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MybatisSqlMapper {
    private final StatementUtils statementUtils;
    private final SqlSession sqlSession;

    public MybatisSqlMapper(@Nonnull SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        if (sqlSession.getConfiguration() != null) {
            this.statementUtils = new StatementUtils(sqlSession.getConfiguration());
        } else {
            this.statementUtils = null;
        }
    }

    /**
     * Returns the single row produced by the given static SQL select statement
     *
     * @param sql the SQL select statement to execute
     *
     * @return the single row mapped as a map of column names to values, or {@code null} if no row is found
     */
    public Map<String, Object> selectOne(@Nonnull String sql) {
        return getOne(selectList(sql));
    }

    /**
     * Returns the single row produced by the given dynamic SQL select statement
     *
     * @param sql the SQL select statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     *
     * @return the single row mapped as a map of column names to values, or {@code null} if no row is found
     */
    public Map<String, Object> selectOne(@Nonnull String sql, @Nullable Object param) {
        return getOne(selectList(sql, param));
    }

    /**
     * Returns the single result of the given static SQL select statement mapped to the given type
     *
     * @param sql the SQL select statement to execute
     * @param resultType the class to map the result row to
     *
     * @return the single result mapped to {@code resultType}, or {@code null} if no row is found
     */
    public <T> T selectOne(@Nonnull String sql, @Nonnull Class<T> resultType) {
        return getOne(selectList(sql, resultType));
    }

    /**
     * Returns the single result of the given dynamic SQL select statement mapped to the given type
     *
     * @param sql the SQL select statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     * @param resultType the class to map the result row to
     *
     * @return the single result mapped to {@code resultType}, or {@code null} if no row is found
     */
    public <T> T selectOne(@Nonnull String sql, @Nullable Object param, @Nonnull Class<T> resultType) {
        return getOne(selectList(sql, param, resultType));
    }

    /**
     * Returns the rows produced by the given static SQL select statement
     *
     * @param sql the SQL select statement to execute
     *
     * @return the result rows, each mapped as a map of column names to values
     */
    public List<Map<String, Object>> selectList(@Nonnull String sql) {
        return sqlSession.selectList(statementUtils.select(sql));
    }

    /**
     * Returns the rows produced by the given dynamic SQL select statement
     *
     * @param sql the SQL select statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     *
     * @return the result rows, each mapped as a map of column names to values
     */
    public List<Map<String, Object>> selectList(@Nonnull String sql, @Nullable Object param) {
        Class<?> parameterType = (param != null) ? param.getClass() : null;
        String statementId = statementUtils.selectDynamic(sql, parameterType);
        return sqlSession.selectList(statementId, param);
    }

    /**
     * Returns the rows produced by the given static SQL select statement mapped to the given type
     *
     * @param sql the SQL select statement to execute
     * @param resultType the class to map each result row to
     *
     * @return the result rows mapped to {@code resultType}
     */
    public <T> List<T> selectList(@Nonnull String sql, @Nonnull Class<T> resultType) {
        return sqlSession.selectList(statementUtils.select(sql, resultType));
    }

    /**
     * Returns the rows produced by the given dynamic SQL select statement mapped to the given type
     *
     * @param sql the SQL select statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     * @param resultType the class to map each result row to
     *
     * @return the result rows mapped to {@code resultType}
     */
    public <T> List<T> selectList(@Nonnull String sql, @Nullable Object param, @Nonnull Class<T> resultType) {
        Class<?> parameterType = (param != null) ? param.getClass() : null;
        return sqlSession.selectList(statementUtils.selectDynamic(sql, parameterType, resultType), param);
    }

    /**
     * Returns the number of rows affected by the given static SQL insert statement
     *
     * @param sql the SQL insert statement to execute
     *
     * @return the number of rows affected by the insert
     */
    public int insert(@Nonnull String sql) {
        return sqlSession.insert(statementUtils.insert(sql));
    }

    /**
     * Returns the number of rows affected by the given dynamic SQL insert statement
     *
     * @param sql the SQL insert statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     *
     * @return the number of rows affected by the insert
     */
    public int insert(@Nonnull String sql, @Nullable Object param) {
        Class<?> parameterType = (param != null) ? param.getClass() : null;
        String statementId = statementUtils.insertDynamic(sql, parameterType);
        return sqlSession.insert(statementId, param);
    }

    /**
     * Returns the number of rows affected by the given static SQL update statement
     *
     * @param sql the SQL update statement to execute
     *
     * @return the number of rows affected by the update
     */
    public int update(@Nonnull String sql) {
        return sqlSession.update(statementUtils.update(sql));
    }

    /**
     * Returns the number of rows affected by the given dynamic SQL update statement
     *
     * @param sql the SQL update statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     *
     * @return the number of rows affected by the update
     */
    public int update(@Nonnull String sql, @Nullable Object param) {
        Class<?> parameterType = (param != null) ? param.getClass() : null;
        String statementId = statementUtils.updateDynamic(sql, parameterType);
        return sqlSession.update(statementId, param);
    }

    /**
     * Returns the number of rows affected by the given static SQL delete statement
     *
     * @param sql the SQL delete statement to execute
     *
     * @return the number of rows affected by the delete
     */
    public int delete(@Nonnull String sql) {
        return sqlSession.delete(statementUtils.delete(sql));
    }

    /**
     * Returns the number of rows affected by the given dynamic SQL delete statement
     *
     * @param sql the SQL delete statement to execute
     * @param param the parameter object used to expand the dynamic SQL statement
     *
     * @return the number of rows affected by the delete
     */
    public int delete(@Nonnull String sql, @Nullable Object param) {
        Class<?> parameterType = (param != null) ? param.getClass() : null;
        String statementId = statementUtils.deleteDynamic(sql, parameterType);
        return sqlSession.delete(statementId, param);
    }

    @Nullable
    private <T> T getOne(@Nonnull List<T> list) {
        int size = list.size();
        if (size == 1) {
            return list.get(0);
        } else if (size > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found more.");
        }
        return null;
    }

    @SuppressWarnings("unused")
    private static class StatementUtils {
        private final Configuration configuration;
        private final LanguageDriver languageDriver;

        private StatementUtils(@Nonnull Configuration configuration) {
            this.configuration = configuration;
            languageDriver = configuration.getDefaultScriptingLanguageInstance();
        }

        private String newStatementId(@Nonnull String sql, @Nonnull SqlCommandType commandType) {
            return StringUtils.join(commandType.toString(), '.', sql.hashCode());
        }

        private boolean hasMappedStatement(@Nonnull String statementId) {
            return configuration.hasStatement(statementId, false);
        }

        private void newSelectStatement(@Nonnull String statementId, @Nonnull SqlSource sqlSource, @Nonnull Class<?> resultType) {
            MappedStatement statement = new MappedStatement.Builder(configuration, statementId, sqlSource, SqlCommandType.SELECT).resultMaps(new ArrayList<>() {
                {
                    add(new ResultMap.Builder(configuration, "defaultResultMap", resultType, new ArrayList<>(0)).build());    // $NON-NLS-1$
                }
            }).build();
            configuration.addMappedStatement(statement);
        }

        private void newUpdateStatement(@Nonnull String statementId, @Nonnull SqlSource sqlSource, @Nonnull SqlCommandType commandType) {
            MappedStatement statement = new MappedStatement.Builder(configuration, statementId, sqlSource, commandType).resultMaps(new ArrayList<>() {
                {
                    add(new ResultMap.Builder(configuration, "defaultResultMap", int.class, new ArrayList<>(0)).build());    // $NON-NLS-1$
                }
            }).build();
            configuration.addMappedStatement(statement);
        }

        private String select(@Nonnull String sql) {
            String statementId = newStatementId(sql, SqlCommandType.SELECT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
            newSelectStatement(statementId, sqlSource, Map.class);
            return statementId;
        }

        private String select(@Nonnull String sql, @Nonnull Class<?> resultType) {
            String statementId = newStatementId(StringUtils.join(resultType, sql), SqlCommandType.SELECT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
            newSelectStatement(statementId, sqlSource, resultType);
            return statementId;
        }

        private String selectDynamic(@Nonnull String sql, @Nullable Class<?> parameterType) {
            String statementId = newStatementId(StringUtils.join(sql, parameterType), SqlCommandType.SELECT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);
            newSelectStatement(statementId, sqlSource, Map.class);
            return statementId;
        }

        private String selectDynamic(@Nonnull String sql, @Nullable Class<?> parameterType, @Nonnull Class<?> resultType) {
            String statementId = newStatementId(StringUtils.join(resultType, sql, parameterType), SqlCommandType.SELECT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);
            newSelectStatement(statementId, sqlSource, resultType);
            return statementId;
        }

        private String insert(@Nonnull String sql) {
            String statementId = newStatementId(sql, SqlCommandType.INSERT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.INSERT);
            return statementId;
        }

        private String insertDynamic(@Nonnull String sql, @Nullable Class<?> parameterType) {
            String statementId = newStatementId(StringUtils.join(sql, parameterType), SqlCommandType.INSERT);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.INSERT);
            return statementId;
        }

        private String update(@Nonnull String sql) {
            String statementId = newStatementId(sql, SqlCommandType.UPDATE);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.UPDATE);
            return statementId;
        }

        private String updateDynamic(@Nonnull String sql, @Nullable Class<?> parameterType) {
            String statementId = newStatementId(StringUtils.join(sql, parameterType), SqlCommandType.UPDATE);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.UPDATE);
            return statementId;
        }

        private String delete(@Nonnull String sql) {
            String statementId = newStatementId(sql, SqlCommandType.DELETE);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.DELETE);
            return statementId;
        }

        private String deleteDynamic(@Nonnull String sql, @Nullable Class<?> parameterType) {
            String statementId = newStatementId(StringUtils.join(sql, parameterType), SqlCommandType.DELETE);
            if (hasMappedStatement(statementId)) {
                return statementId;
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);
            newUpdateStatement(statementId, sqlSource, SqlCommandType.DELETE);
            return statementId;
        }
    }
}
