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

# 大数据算法模型的文档编写规则
![avatar](https://github.com/qiushangwenyue/HadoopDemo/blob/master/resource/bigDataModelDesign.png)

### yarn application --list or --kill
https://www.cnblogs.com/0xcafedaddy/p/8487340.html

# docker搭建大数据集群Test
参考资料：<br/>
https://blog.csdn.net/cbl709/article/details/43955687<br/>
https://blog.csdn.net/justhavetry/article/details/72901329<br/>
https://blog.csdn.net/larger5/article/details/81153699<br/>
http://bigdata.51cto.com/art/201702/529889.htm<br/>
https://www.cnblogs.com/kex1n/p/6933039.html<br/>
