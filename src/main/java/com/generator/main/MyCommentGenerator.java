package com.generator.main;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.generator.main.Constant.*;

/**
 * @desc 实体类生成类
 * @author lvshen
 * @date 2019/12/26
 */
public class MyCommentGenerator extends EmptyCommentGenerator {
    private final Collection<Annotations> annotations;
    private String author;
    /**
     * 当前时间
     */
    private String currentDateStr;

    public MyCommentGenerator() {
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        annotations = new LinkedHashSet<>(Annotations.values().length);
    }


    @Override
    public void addConfigurationProperties(Properties properties) {

        annotations.add(Annotations.DATA);
        annotations.add(Annotations.EQUALS_HASH);

        for (String stringPropertyName : properties.stringPropertyNames()) {
            if (stringPropertyName.contains(".")) {
                continue;
            }
            if (!Boolean.parseBoolean(properties.getProperty(stringPropertyName))) {
                continue;
            }
            Annotations annotation = Annotations.getValueOf(stringPropertyName);
            if (annotation == null) {
                continue;
            }

            String optionsPrefix = stringPropertyName + ".";
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                annotation.appendOptions(propertyName, propertyValue);
                annotations.add(annotation);
                annotations.addAll(Annotations.getDependencies(annotation));
            }
            annotations.add(annotation);
        }
        author = properties.getProperty("author");
        mapData.put("author",author);
    }


    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @Description  " + tableName + ":" + remarks);
        topLevelClass.addJavaDocLine(" * @author   " + author);
        topLevelClass.addJavaDocLine(" * @date   " + currentDateStr);
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@Table(name = \"" + tableName + "\")");
        addClassAnnotation(topLevelClass);
    }

    private void addClassAnnotation(TopLevelClass topLevelClass) {
        for (Annotations annotation : annotations) {
            topLevelClass.addImportedType(annotation.javaType);
            topLevelClass.addAnnotation(annotation.asAnnotation());
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        String columnName = introspectedColumn.getActualColumnName();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + columnName + ":" + remarks);
        field.addJavaDocLine(" */");

        //给model的字段添加swagger注解
        field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\")");

        // createdTime或者updatedTime时间格式化
        if (DATE_TYPE.equals(field.getType().getShortName())) {
            field.addJavaDocLine("@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")");
        }

        field.addJavaDocLine("@Column(name = \"" + columnName + "\")");

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        //只在model中添加swagger注解类的导入
        if (!compilationUnit.isJavaInterface() && !compilationUnit.getType().getFullyQualifiedName().contains(Constant.EXAMPLE_SUFFIX)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_JSON_FORMAT));
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_COLUMN));
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_TABLE_NAME));
        }
        //生成的是Mapper文件
        if (compilationUnit.isJavaInterface()) {
            Interface anInterface = (Interface)compilationUnit;
            anInterface.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
            anInterface.addAnnotation("@Repository");
        }
    }
}

