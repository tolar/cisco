package cz.vaclavtolar.cisco;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphProcessorTest {

    private static GNode tree0;
    private static GNode tree1;
    private static GNode tree2;

    private static GraphProcessor graphProcessor;

    @BeforeClass
    public static void testSetup() {

        graphProcessor = new GraphProcessorImpl();

        tree0 = new Node("1");

        tree1 = new Node("1",
                new GNode[] {
                        new Node("21"),
                        new Node("22")
                });

        tree2 = new Node("1",
                new GNode[] {
                        new Node("21",
                                new GNode[] {
                                        new Node("31",
                                                new GNode[] {
                                                        new Node("41")}),
                                        new Node("32")
                                }),
                        new Node("22")
                });
    }

    @Test
    public void testWalkGraph() {

        System.out.println("testWalkGraph");

        List<GNode> result0 = graphProcessor.walkGraph(tree0);
        System.out.println("result0:" + result0);
        Assert.assertEquals("Unexpected size of node list", 1, result0.size());
        Set<GNode> reducedResult0 = new HashSet<GNode>(result0);
        Assert.assertEquals("Found duplicates in node list", reducedResult0.size(), result0.size());
        
        List<GNode> result1 = graphProcessor.walkGraph(tree1);
        System.out.println("result1:" + result1);
        Assert.assertEquals("Unexpected size of node list", 3, result1.size());
        Set<GNode> reducedResult1 = new HashSet<GNode>(result1);
        Assert.assertEquals("Found duplicates in node list", reducedResult1.size(), result1.size());

        List<GNode> result2 = graphProcessor.walkGraph(tree2);
        System.out.println("result2:" + result2);
        Assert.assertEquals("Unexpected size of node list", 6, result2.size());
        Set<GNode> reducedResult2 = new HashSet<GNode>(result2);
        Assert.assertEquals("Found duplicates in node list", reducedResult2.size(), result2.size());

    }


    @Test
    public void testPaths() {

        System.out.println("testPaths");

        List<List<GNode>> result0 = graphProcessor.paths(tree0);
        System.out.println("result0:" + result0);

        List<List<GNode>> result1 = graphProcessor.paths(tree1);
        System.out.println("result1:" + result1);

        List<List<GNode>> result2 = graphProcessor.paths(tree2);
        System.out.println("result2:" + result2);

    }


    static class Node implements GNode {

        private String name;
        private GNode[] children;

        public Node(String name, GNode[] children) {
            this.name = name;
            this.children = children;
        }

        public Node(String name) {
            this(name, new GNode[0]);
        }

        public String getName() {
            return name;
        }

        public GNode[] getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return name.equals(node.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
