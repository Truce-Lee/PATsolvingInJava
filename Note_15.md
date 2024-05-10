#### 1015

注意边界值的测试，如1不是质数。

对边界值的考虑最好不要过于特殊化。//因为对这题仍然有疑惑
#### 1016
###### first attempt

标准化输出，需要在整数前补齐0的情况：

````java
String.format("%s %02d", name,month)
````

暴力算法，通过测试点0，测试点1非零返回，测试点2、3超时

###### second attempt

1. **修复`compareTo`拼写错误和排序逻辑**：修正`Time`类的`comparoTo`拼写错误，且确保它返回负数、零或正数。还需要确保各个字段比较得当。

2. **修正费用计算逻辑**：优化`getCost`方法，准确处理跨天或跨小时的通话费用计算，且不要使用不必要的`100`的乘除。

3. **输入数据校验**：在读取输入数据时，校验输入格式，并确保每对`on-line`和`off-line`之间合理匹配。

4. **处理时间记录配对**：在`Record`类中增加逻辑处理匹配`on-line`和`off-line`状态，防止出现未匹配记录。

5. **性能改进**：对于数据结构，可以使用`TreeMap`进行存储和排序。

通过测试点1，测试点2、3仍然超时

#### 1019

简单题，首次尝试未通过测试点2与测试点4，问题在于对高于十进制数的情况的考虑，并且需要考虑高于26进制的情况！即用字母无法表示一位的数字。

解法在于用ArrayList存储每一个余数。

三目运算符和printf的使用

````java
System.out.printf("%d%s",list.get(i), i==0? "\n" :" ");
````

#### 1020

二叉树前序排序(preorder)、中序排序(inorder)、后序排序(postorder)、层序排序(level order)的定义

算法核心思想在于，根据节点在后序排序的位置确定根节点，在中序排序的位置确定子树，递归遍历此操作。

###### first attempt

用数组存储二叉树，勉强实现功能，通过测试点0；

###### second attempt

一、常见的二叉树数据结构构造方法：

1. 定义Node类：

````java
static class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value
    }
}
````

2. 递归,建立树:

````java
private static Node buildTree(postOrder;inMap;startPos;endPos;startIn;endIn){
	if(startPos>endPos || startIn>endIn){
        return null
    }
    root = posOrder[endPos];
    rootIn = inMap.get(root);
    leftTree = rootIn - startIn;
    rightTree = endIn - rootIn;
    root.left= buildTree(postOrder,inMap,startPos,startPos+leftTree-1,startIn,rootIn-1);
    root.right=buildTree(postOrder,inMap,endPos-rightTree,endPos-1,rootIn+1,endIn);
    return root;
}
````

二、广度优先搜索：

````java
Queue<Node> queue = new LinkedList<>();
if(root!=null) queue.offer(root);
While(!queue.isEmpty()){
    Node node = queue.poll();
    order.add(Node);
    if(node.left!=null) queue.offer(node.left);
    if(node.right!=null) queue.offer(node.right);
}
````

注意点：

Java内部队列是一个接口，常常使用LinkedList<>实现队列；其对内部元素删除添加的操作为O(1)

````java
Queue<Node> queue = new LinkedList<>();
````

add/offer,poll/peak的区别：

​	add在添加元素时若超出内存为扔出异常；offer返回false;

​	poll取出queue中的元素（FIFO）,peak返回元素后不改变queue的状态
