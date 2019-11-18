package huskymaps.server.logic;

import huskymaps.params.RasterRequest;
import huskymaps.params.RasterResult;

import java.util.Objects;

import static huskymaps.utils.Constants.*;

/** Application logic for the RasterAPIHandler. */
public class Rasterer {

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param request RasterRequest
     * @return RasterResult
     */
    public static RasterResult rasterizeMap(RasterRequest request) {
        int depth = request.depth;
        double lonPerTile = LON_PER_TILE[depth];
        double latPerTile = LAT_PER_TILE[depth];
        int numXTilesDepth = NUM_X_TILES_AT_DEPTH[depth];
        int numYTilesDepth = NUM_Y_TILES_AT_DEPTH[depth];
        int startTileX = (int) ((request.ullon - ROOT_ULLON)/lonPerTile);
        int endTileX = (int) ((request.lrlon - ROOT_ULLON)/lonPerTile)+1;
        int startTileY = (int) ((ROOT_ULLAT - request.ullat)/latPerTile);
        int endTileY = (int) ((ROOT_ULLAT - request.lrlat)/latPerTile)+1;
        //        if (request.ullon - ROOT_ULLON < 0){
        //            startTileX = 0;
        //            endTileX += ROOT_ULLON - request.ullon;
        //        }
        //        if (ROOT_ULLAT - request.ullat < 0){
        //            startTileY = 0;
        //            endTileY += request.ullat - ROOT_ULLAT;
        //        }
        //        if (request.lrlon > ROOT_LRLON){
        //            startTileX = numXTilesDepth - (endTileX - startTileX);
        //            endTileX = numXTilesDepth;
        //        }
        //        if (request.lrlat < ROOT_LRLAT){
        //            startTileY = numYTilesDepth - (endTileY - startTileY);
        //            endTileY = numYTilesDepth;
        //        }
        //        if (depth == 0){
        //            startTileX =0;
        //            endTileX =2;
        //            startTileY = 0;
        //            endTileY =1;
        //        }


        int yTiles = endTileY-startTileY;
        int xTiles = endTileX-startTileX;
        Tile[][] grid2d = new Tile[yTiles][xTiles];
        for (int i = startTileX; i < endTileX; i++) {
            for (int j = startTileY; j < endTileY; j++) {
                grid2d[j-startTileY][i-startTileX] = new Tile(request.depth, i, j);
            }
        }
        return new RasterResult(grid2d);
    }

    public static class Tile {
        public final int depth;
        public final int x;
        public final int y;

        public Tile(int depth, int x, int y) {
            this.depth = depth;
            this.x = x;
            this.y = y;
        }

        public Tile offset() {
            return new Tile(depth, x + 1, y + 1);
        }

        /**
         * Return the latitude of the upper-left corner of the given slippy map tile.
         * @return latitude of the upper-left corner
         * @source https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
         */
        public double lat() {
            double n = Math.pow(2.0, MIN_ZOOM_LEVEL + depth);
            int slippyY = MIN_Y_TILE_AT_DEPTH[depth] + y;
            double latRad = Math.atan(Math.sinh(Math.PI * (1 - 2 * slippyY / n)));
            return Math.toDegrees(latRad);
        }

        /**
         * Return the longitude of the upper-left corner of the given slippy map tile.
         * @return longitude of the upper-left corner
         * @source https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
         */
        public double lon() {
            double n = Math.pow(2.0, MIN_ZOOM_LEVEL + depth);
            int slippyX = MIN_X_TILE_AT_DEPTH[depth] + x;
            return slippyX / n * 360.0 - 180.0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Tile tile = (Tile) o;
            return depth == tile.depth &&
                    x == tile.x &&
                    y == tile.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(depth, x, y);
        }

        @Override
        public String toString() {
            return "d" + depth + "_x" + x + "_y" + y + ".jpg";
        }
    }
}
