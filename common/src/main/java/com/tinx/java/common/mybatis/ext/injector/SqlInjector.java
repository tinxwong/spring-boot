package com.tinx.java.common.mybatis.ext.injector;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.tinx.java.common.core.Model;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tinx
 * @date 2018-10-26 0:07
 */
public class SqlInjector extends AutoSqlInjector {

    private Set<String> RECALC_MAP = new HashSet<String>();

    @Autowired
    public void inject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        this.configuration = builderAssistant.getConfiguration();
        this.builderAssistant = builderAssistant;
        this.languageDriver = this.configuration.getDefaultScriptingLanguageInstance();
        GlobalConfiguration globalCache = this.getGlobalConfig();
        if(!globalCache.isDbColumnUnderline()) {
            globalCache.setDbColumnUnderline(this.configuration.isMapUnderscoreToCamelCase());
        }

        Class modelClass = this.extractModelClass(mapperClass);
        if(modelClass != null) {
            TableInfo table = TableInfoHelper.initTableInfo(builderAssistant, modelClass);
            reCalcTableName(table, modelClass);
            this.injectSql(builderAssistant, mapperClass, modelClass, table);
        }
    }

    /**
     * 重新计算
     * @param tableInfo
     * @param modelClass
     */
    private void reCalcTableName(TableInfo tableInfo, Class modelClass) {
        if (RECALC_MAP.contains(modelClass.getName())) return;
        RECALC_MAP.add(modelClass.getName());

        String tableName = null;
        do {
            tableName = getTableName(modelClass);
            modelClass = modelClass.getSuperclass();
        } while (tableName == null && modelClass != null);

        if (StringUtils.isNotEmpty(tableName)) {
            tableInfo.setTableName(tableName);
        }
    }

    private String getTableName(Class modelClass) {
        if (modelClass == null) return null;
        TableName table = (TableName)modelClass.getAnnotation(TableName.class);
        if (table != null) {
            return table.value();
        }
        // 添加自定义标签支持
        Model model = (Model)modelClass.getAnnotation(Model.class);
        if (model != null) {
            return model.tableName();
        }
        return null;
    }

}
