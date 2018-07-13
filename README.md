# 运行jar包的两种模式
* 使用java -jar运行<br/>
  1.resources目录下的core-site.xml、hdfs-site.xml、mapred-site.xml、yarn-site.xml四个文件需要添加<br/>
  2.需要明确指定运行的jar包所在的目录路径，如WordcountDriver.java文件中指定wc.jar所在的路径：<br/>
    > //使用java -jar命令运行wc.jar包时，需要使用以下命令
      		job.setJar("/home/hadoop/wc.jar");
  
  3.需要把所有依赖的jar包连同自写的代码都打包成一个jar包，即带有依赖的wc.jar<br/>
  4.在hadoop集群中运行命令，要指定运行的类名<br/>
  > java -jar HadoopDemo.jar cn.itcast.bigdata.mr.wcdemo.WordcountDriver /flowsum/input /flowsum/output
  
* 使用hadoop jar运行<br/>
  1.resources目录下的core-site.xml、hdfs-site.xml、mapred-site.xml、yarn-site.xml四个文件不需要添加<br/>
  2.不需要明确指定运行的jar包所在的目录路径，如WordcountDriver.java文件中替换上一种模式的代码为：<br/>
  > //这个是使用hadoop jar运行wc.jar包时，需要使用的命令。指定本程序的jar包所在路径
    		job.setJarByClass(WordcountDriver.class);
    		
  3.只需要打包一个不带依赖的jar包即可<br/>
  4.在hadoop集群中运行命令，不需要指定运行的类名，因为打包的时候已经指定运行的main类，即META-INF中的MANIFEST.MF已经指定：<br/>
  > hadoop jar HadoopDemo.jar /flowsum/input /flowsum/output

# 大数据平台架构技术选型与场景运用
参考资料：https://blog.csdn.net/leishenop/article/details/73527467

# 大数据的团队角色分类
参考资料：https://www.jianshu.com/p/7383cc6f7933

# 大数据的创建流程
参考资料：https://www.zhihu.com/question/37627092

# 大数据的数据库混合使用模式
参考资料：http://www.h3c.com/cn/d_201511/901094_30008_0.htm

# 大数据平台中关系型数据库、DB2、HBase分别做什么
关系型数据库是小型前台报表库，存放大数据加工好的数据，前台展示用。DB2则是历史原因，用于集团接口数据库，后期将逐步演进为分布式架构，HBase主要用于高并发查询，如日志查询，它的数据来源于ETL加工处理好的明细数据。
