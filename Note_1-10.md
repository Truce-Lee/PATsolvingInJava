#### 1001

Scanner类的用法

Java使用String类进行标准化输出 

````java
String.format("%,d",sum);
````

注意 “，”

#### 1002

TreeMap Java内部基于红黑树的Map实现 内部元素一直是依照key有序排序的

初始化时必须实现comparable接口 

lamda表达式： （a,b）->a-b 保证按照key降序排序

````java
TreeMap<Integer,Double> poly = TreeMap<>((a,b)->a-b);
````

TreeMap内部的merge方法：插入key,value;Double::sum处理key相同情况，接收两个Double类型并相加

````java
poly1.merge(eoff, poly2.get(eoff), Double::sum)
````

TreeMap内部的removeIf方法：在某条件下删除某个value;	lamda表达式 对于Double类型的计算考虑精度，不是简单的0

````java
poly1.values().removeIf(v -> Math.abs(v) < 1e-10);
````

for 遍历TreeMap中每一个key

````java
for(Integer eoff:poly.keySet())
````

StringBuilder类的用法

````java
sb.append(result.size());
sb.toString();
````

对于输出格式的要求：空格在前

#### 1003

###### first attempt

使用深度优先搜索进行深度递归遍历：通过首个测试，未通过其他测试并超时

对多维数组的遍历：

````java
for(int[]road:roads)
````

三元运算符的写法：

````java
int nextCity = road[0]==currentCity?road[1]:road[0];
````

DFS递归算法的写法：

````java
//标记已经寻找该点
//判断当前是否达到目标节点
	//若未达到，继续递归遍历
//删除当前节点的标记，回溯
````

###### second attempt

不使用二维数组，而是使用邻接列表作为数据结构，邻接列表更适用于稀疏图:但是仍然未通过其他测试，但是不再存在超时问题

````java
List<List<Pair<Integer,Integer>>> graph = new ArrayList<>();
````

自定义Pair类

````java
static class Pair<T, U>{
		public T first;
		public U second;
		public Pair(T first,U second){
			this.first = first;
			this.second = second;
		}
	}
````

使用List的get方法和add方法构建数据结构

递归之前更新值需要初始化新的变量，否则在return后变量已赋值可能会造成错误

````java
int newLength = currentLength + pair.second;
int newTeamNum = currentTeamNum + rescueTeam[nextCity];
````

不要忘记删除当前节点的标记

````java
visitedCity[currentCity] = false;
````

考虑了起点终点相同的情况，通过了第二个测试

````java
        if (start == end) {
            System.out.println(1 + " " + rescueTeam[start]);
            return; 
        }
````



###### third attempt

发现问题所在。。。题目问的是 the number of different shortest paths between *C*1 and *C*2，即最短路径的数量，而我下意识认为题目所求的是最短路径的长度。。。。注意审题注意审题！！！！！！

并且在无负权边的情况下，Dijkstra是比DFS更优的解法。后续遇到相关问题时应用Dijkstra算法重写该题。

#### 1004

###### first attempt

尝试自己写一个树的数据结构

自定义类内部的所有内部变量需要在public方法中初始化:

````java
this.children=new ArrayList<>();
````

Scanner读取一行中空格分隔的String方法：

````java
String[] parts = line.split(" ");
````

String转int:

````java
int numChildren = Integer.parseInt(parts[1]);
````

使用scanner.nextLine()时特别注意换行符要额外读取

````java
int nonLeafNode = scanner.nextInt();
scanner.nextLine(); // 这行将消耗掉整数后的换行符
````

通过两个测试

重新读题，确认题目要求，通过三个测试用例

关于自增：

````java
count[i]++;
sum++;
````

正确写法如上；在 Java 中，`x++` 是先返回 `x` 的当前值，然后 `x` 的值增加1

###### second attempt

两个关键点：

1.使用HashMap数据结构，在创建树时，先考虑增加的节点是否已经存在，这样可以保证孙子节点的正确添加

````java
Node node= tree.getOrDefault(id, new Node(id));
````

使用getOrDefault来实现上述功能

2.使用BFS，来确保输出的是每一个layer的叶子节点

````java
while(!queue.isEmpty()) {
	int size = queue.size();
    int count=0;
    for(int i =0; i<size;i++) {
        Node current = queue.poll();
        if(current.children.isEmpty()) {
            count++;
        }else {
            queue.addAll(current.children);
        }
    }
    counts.add(count);
}
````

使用两个循环，用LinkedList创建队列

通过四个测试，还有两个测试存在错误：分别为测试点2于测试点4

经过多次实验，确定测试点2的输入为1，0 即总点数为1的情况，原程序中会导致HashMap空指针出错。

经过对Exception的catch，确定测试点4在于分割符号为连续空格的情况，源程序中

````java
String[] parts = line.split(" ");
````

的方法会产生错误，处理方法为

````java
String[] parts = line.split("\\s+");
````

使用正则表达式来匹配一个或者多个空格。

最终通过测试。
#### 1005

题目简单，一遍过。

写法比较丑陋

char转int的方法：

````java
int index =Character.getNumericValue(string.charAt(i));
````

#### 1006
###### first attempt

题目无难度

关注Eclipse使用debug时如何查看变量值
#### 1007

经典的动态规划题

注意审题，输出的是起始和终止的数字；并且在全负数的情况下的特殊输出要求

###### first attempt

测试点5未通过，7超时

Eclipse ctrl+1快速修复
###### second attempt

对于测试点7超时，考虑用BufferedReaderInputStreamReader来代替Scanner

````java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
````

````java
String[] inputs = br.readLine().trim().split(" ");
seq[i] = Integer.parseInt(inputs[i]);
````

并且，将全局最大值max定义为Integer.MIN_VALUE后，通过测试

例如对于测试用例

````java
4
-1 -2 -3 -4
````

正确的输出是0 -1 -4，但若未初始化max未负数，则其无法对首位两个数的索引赋值，从而报错。
#### 1008

无难度

注意input的格式
#### 1009

与1002类似，可以根据经验判断使用TreeMap结构存储多项式是合理的

问题在于对TreeMap的各类方法仍不熟悉，最重要的在一下这三种：

````java
TreeMap<Integer,Double> poly1 = new TreeMap<>((a,b)->b-a);
````

初始化，`（b-a）`意味着降序

````java
multiple.merge(exp1+exp2, poly1.get(exp2)*poly2.get(exp1),Double::sum);
````

merge函数的使用，常常意味着多项式的加法。重点在于`Double::sum`的写法

````java
multiple.values().removeIf(v->Math.abs(v)<1e-10);
````

以及`removeIf`方法在.value方法下`(v->Math.abs(v)<1e-10)`，此方法接受一个谓词

注意科学计数法小数的表示

并且需要记住lamda表达式`->`的写法和作用：左参数，右执行体，用以表示匿名函数，

`v`即为`multiple.values()`集合中的每一个值,由`removeIf`方法定义

除此以外`String.format("%,.1f", poly1.get(exp)));`格式化输出的方法仍需注意

需了解知识：lamda表达式， `Collection` 接口，`Comparator`比较器
#### 1010
###### first attempt

暴力穷举，测试点0 7 10 19未通过

注意Java的乘法算法即使是整数型也用Math.pow

###### second attempt

将int改为long,并且使用二分法计算

radix的最小值为各个位数中值的最大值＋1；radix的最大值为需比较的十进制数的值＋1；

特别注意最小值的设定不能出错，否则当出现N1==N2时（测试点0）可能会出现错误




