package piece;


import java.util.concurrent.atomic.AtomicLong;

public class PuzzlePiece {

    private static final AtomicLong idCounter = new AtomicLong(1);

    private final String id;
    private final PieceType pieceType;
    // NSEW
    private final Edge[] edges = new Edge[4];
    private Orientation orientation;
    public PuzzlePiece(PieceType pieceType) {
        id = pieceType.name().charAt(0) + "_" + idCounter.getAndIncrement();
        this.pieceType = pieceType;
        this.orientation = Orientation.NORTH;
    }

    public void addEdge(Orientation side, EdgeType edgeType, int curvature) {
        System.out.println("[PuzzlePiece] " + id + " adding new edge at " + side);
        Edge newEdge = new Edge(edgeType, curvature);
        validateNewEdge(newEdge, side);
        edges[side.ordinal()] = newEdge;
    }

    private Orientation getDirectionDuringFit(Orientation otherDirection) {
        return switch (otherDirection) {
            case EAST -> Orientation.WEST;
            case WEST -> Orientation.EAST;
            case SOUTH -> Orientation.NORTH;
            default -> Orientation.SOUTH;
        };
    }


    public boolean fit(PuzzlePiece other, Orientation toWhichDirection) {
        System.out.println("[PuzzlePiece] " + id + " trying to fit with " + other.getId() + " at " + toWhichDirection + " currently oriented " + orientation);
        Edge myEdge = getEdge(toWhichDirection);
        Edge othersEdge = other.getEdge(getDirectionDuringFit(toWhichDirection));

        return matchEdge(myEdge, othersEdge);
    }

    private boolean matchEdge(Edge edge1, Edge edge2) {
        System.out.println("[PuzzlePiece] Trying to match " + edge1 + " with " + edge2);
        return (edge1.edgeType() == EdgeType.INDENTATION && edge2.edgeType() == EdgeType.EXTRUSION ||
                edge1.edgeType() == EdgeType.EXTRUSION && edge2.edgeType() == EdgeType.INDENTATION)
                && edge1.curvature() == edge2.curvature();
    }


    public Edge getEdge(Orientation side) {
        int actualIndex = (side.ordinal() - orientation.ordinal() + 4) % 4;
        System.out.println("[PuzzlePiece] " + id + " currently oriented " + orientation + " returning " + side + " edge, giving " + Orientation.values()[actualIndex]);
        return edges[actualIndex];
    }

    public Orientation getOrientation() { return orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public String getId() { return id; }

    public void validate() {
        int flat = countFlatEdges();
        for (Edge edge : edges) {
            if (edge == null)
                throw new IllegalArgumentException("Piece " + id + " doesn't have all edges configured");
        }
        validateFlat(flat);
    }

    private int countFlatEdges() {
        int flat = 0;
        for (Edge edge : edges) {
            if (edge == null) continue;
            if (edge.edgeType() == EdgeType.FLAT)
                flat++;
        }
        return flat;
    }

    private void validateNewEdge(Edge newEdge, Orientation side) {
        // Count the edge types
        int flat = countFlatEdges();
        // Remove the edge currently at the chosen side (if any)
        if (edges[side.ordinal()] != null &&
                edges[side.ordinal()].edgeType() == EdgeType.FLAT)
            flat--;

        // Now for the new edge getting added:
        if (newEdge.edgeType() == EdgeType.FLAT)
            flat++;
        validateFlat(flat);
    }

    private void validateFlat(int flat) {
        // if the piece is a corner piece, only 2 flat edges allowed:
        if (pieceType == PieceType.CORNER && flat > 2)
            throw new IllegalArgumentException("Piece should only have 2 flat edges - currently has " + flat);
        // If Edge piece, only 1 flat edge allowed
        if (pieceType == PieceType.EDGE && flat > 1)
            throw new IllegalArgumentException("Piece should only have 1 flat edge - currently has " + flat);
        // If middle, no flat edges allowed:
        if (pieceType == PieceType.MIDDLE && flat > 0)
            throw new IllegalArgumentException("Piece should only have no flat edge - currently has " + flat);
    }
}
