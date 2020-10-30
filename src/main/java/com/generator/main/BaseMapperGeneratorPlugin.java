package com.generator.main;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Description:
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020/10/29 16:52
 * @since JDK 1.8
 */
public class BaseMapperGeneratorPlugin extends PluginAdapter {

    private String rootMapperInterface;

    @Override
    public boolean validate(List<String> warnings) {
        rootMapperInterface = properties.getProperty("rootMapperInterface");
        rootMapperInterface = stringHasValue(rootMapperInterface) ? rootMapperInterface : "";
        return true;
    }

    /**
     * 生成mapper
     */
    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String[] split = StringUtils.split(rootMapperInterface);

        if (split != null && split.length > 0) {
            String baseMapperStr = split[split.length - 1];

            //拼接父类接口
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(baseMapperStr + "<"
                    + introspectedTable.getBaseRecordType() + ">");
            FullyQualifiedJavaType imp = new FullyQualifiedJavaType(rootMapperInterface);

            //添加 extends MybatisBaseMapper
            interfaze.addSuperInterface(fqjt);

            //导入类;
            interfaze.addImportedType(imp);
        }

        //方法不需要
        interfaze.getMethods().clear();
        return true;
    }
}
