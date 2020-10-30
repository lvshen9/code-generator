package com.generator.main;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: Main
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020/7/4 11:09
 * @since JDK 1.8
 */
public class Main {
    public static void main(String[] args){
        System.out.println("开始生成代码....");
        List<String> warnings = new ArrayList<>();
        String genCfg = "/runtimecfg/generatorConfig.xml";
        File configFile = new File(Main.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("代码生成完成!!!");

    }
}
