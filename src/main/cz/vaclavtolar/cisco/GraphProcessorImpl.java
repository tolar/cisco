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
        paths(start, start, new ArrayList<GNode>(), result);
        return result;
    }

    private void paths(GNode start, GNode current, ArrayList<GNode> currentPath, List<List<GNode>> result) {

        currentPath.add(current);

        if (current.getChildren().length > 0) {
            // there are
            for (GNode child : current.getChildren()) {
                paths(start, child, currentPath, result);
            }
        } else {
            // we have reached a leaf
            ArrayList<GNode> foundPath = (ArrayList<GNode>) currentPath.clone();
            result.add(foundPath);
        }



    }
}
