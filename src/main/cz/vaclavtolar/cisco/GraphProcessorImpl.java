package cz.vaclavtolar.cisco;

import java.util.ArrayList;
import java.util.List;

public class GraphProcessorImpl implements GraphProcessor {

    public List<GNode> walkGraph(GNode node) {

        final List<GNode> result = new ArrayList<GNode>();
        result.add(node);

        for (GNode child : node.getChildren()) {
            result.addAll(walkGraph(child));
        }

        return result;
    }

    public List<List<GNode>> paths(GNode start) {
        final List<List<GNode>> result = new ArrayList<List<GNode>>();
        paths(start, new ArrayList<GNode>(), result);
        return result;
    }

    private void paths(GNode current, ArrayList<GNode> currentPath, List<List<GNode>> result) {
        currentPath.add(current);
        if (current.getChildren().length > 0) {
            // there are children
            for (GNode child : current.getChildren()) {
                ArrayList<GNode> currentPathForChild = (ArrayList<GNode>) currentPath.clone();
                paths(child, currentPathForChild, result);
            }
        } else {
            // we have reached a leaf
            result.add(currentPath);
        }
    }
}
