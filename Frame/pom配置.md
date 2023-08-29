``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
    <!--声明项目描述符遵循哪一个POM模型版本。模型本身的版本很少改变，虽然如此，但它仍然是必不可少的，这是为了当Maven引入了新的特性或者其他模型变更的时候，确保稳定性。 -->  
    <modelVersion>4.0.0</modelVersion>  
    
    <!--父项目的坐标。如果项目中没有规定某个元素的值，那么父项目中的对应值即为项目的默认值。 坐标包括group ID，artifact ID和 version。 --> 
    <parent>   
        <groupId>org.springframework.boot</groupId>  <!--被继承的父项目的全球唯一标识符 --> 
        <artifactId>spring-boot-starter-parent</artifactId>  <!--被继承的父项目的构件标识符 -->  
        <version>2.3.12.RELEASE</version>  <!--被继承的父项目的版本 -->
        <relativePath />   <!--父项目的pom.xml文件的相对路径。相对路径允许你选择一个不同的路径。默认值是../pom.xml。Maven首先在构建当前项目的地方寻找父项目的pom，其次在文件系统的这个位置（relativePath位置），然后在本地仓库，最后在远程仓库寻找父项目的pom。 --> 
    </parent>  
    
    <groupId>com.lsl</groupId>  <!--项目的全球唯一标识符，通常使用全限定的包名区分该项目和其他项目。-->
    <artifactId>Frame</artifactId>  <!--构件的标识符，它和group ID一起唯一标识一个构件。换句话说，你不能有两个不同的项目拥有同样的artifact ID和groupID；在某个特定的group ID下，artifact ID也必须是唯一的。构件是项目产生的或使用的一个东西，Maven为项目产生的构件包括：JARs，源码，二进制发布和WARs等。 -->  
    <packaging>pom</packaging>  <!--项目产生的构件类型，例如jar、war、ear、pom。插件可以创建他们自己的构件类型，所以前面列的不是全部构件类型 -->  
    <version>1.0-SNAPSHOT</version>  <!--项目当前版本，格式为:主版本.次版本.增量版本-限定版本号 -->
    <name>Frame</name>  <!--项目的名称, Maven产生的文档用 --> 
    <description>A maven project to study maven.</description>  <!--项目的详细描述, Maven 产生的文档用。 -->  
      
    <!--定义项目中的属性，可以在整个pom.xml文件中引用，这些属性可以包括版本号、路径等-->
    <properties>
        <java.version>8</java.version> <!--java版本号-->
    </properties>
    
    <!--在一个多模块项目中定义子模块-->
    <modules> 
        <module></module> <!--填写子模块名称-->
    </modules>
    
    <!--声明项目的依赖关系，包括第三方库和其他模块等-->
    <depencyManagement> <!--该标签用于集中管理项目的依赖版本，子项目可以简单的引用父项目声明的库而无需重复指定版本号-->
        <dependencies>
            <dependency> <!--声明一个依赖项-->
                <groupId>mysql</groupId> <!--依赖库的组织或公司唯一标识符-->
                <artifactId>mysql-connector-java</artifactId> <!--依赖库的唯一标识符-->
                <version>8.0.30</version> <!--依赖库的版本号-->
            </dependency>
        </dependencies>
    </depencyManagement>
    
    <!-配置项目的构建过程，包括插件配置和其他构建相关的设置-->
    <build>
        <plugins> <!--声明一个或多个构建插件-->
            <plugin> <!--配置一个构建插件-->
                <groupId>org.springframework.boot</groupId> <!--插件的组织或公司唯一标识符-->
                <artifactId>spring-boot-maven-plugin</artifactId> <!--插件的唯一标识符-->
                <configuration> <!--定义插件的特定配置信息-->
                    <excludes> <!--排除指定的文件或目录-->
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
</project>   
```