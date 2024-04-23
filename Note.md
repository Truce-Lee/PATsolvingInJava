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