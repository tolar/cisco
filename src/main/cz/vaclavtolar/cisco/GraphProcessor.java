package cz.vaclavtolar.cisco;

import java.util.List;

public interface GraphProcessor {

    /** Task no. 1 */
    List<GNode> walkGraph(GNode node);

    /** Task no. 2 */
    List<List<GNode>> paths(GNode node);
}
