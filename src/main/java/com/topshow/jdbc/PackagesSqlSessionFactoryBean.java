package com.topshow.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {
    private static Logger logger = LogManager.getLogger(PackagesSqlSessionFactoryBean.class.getName());

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory(
                pathMatchingResourcePatternResolver);

        typeAliasesPackage = "classpath*:" + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/"
                + "**/*.class";

        List<String> result = new ArrayList<String>();
        try {
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(typeAliasesPackage);
            if (resources != null && resources.length > 0) {
                MetadataReader metadataReader = null;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                        try {
                            result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage()
                                    .getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if (result.size() > 0) {
                super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));
            } else {
                logger.warn("参数typeAliasesPackage:" + typeAliasesPackage + ",未找到任何包");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
