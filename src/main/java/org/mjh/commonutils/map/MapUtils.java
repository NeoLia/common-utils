package org.mjh.commonutils.map;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author Neo Lia
 * @date 2023/5/8 17:08
 */
public class MapUtils {
    /**
     * WGS84坐标系中的地球长半径（单位：米）
     */
    private static final double EARTH_RADIUS_WGS84 = 6378137d;

    /**
     * 在WGS-84坐标系下，计算两个坐标点之间的距离
     * @param longitude1 - 坐标点1经度
     * @param latitude1 - 坐标点1纬度
     * @param longitude2 - 坐标点2经度
     * @param latitude2 - 坐标点2纬度
     * @return double - 返回距离
     * @author Neo Lia
     */
    public static double calculateWgs84Distance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radianLatitude1 = Math.toRadians(latitude1);
        double radianLatitude2 = Math.toRadians(latitude2);
        double radianLongitude1 = Math.toRadians(longitude1);
        double radianLongitude2 = Math.toRadians(longitude2);

        double a = radianLatitude1 - radianLatitude2;
        double b = radianLongitude1 - radianLongitude2;
        double s = 2 * Math.asin(
                Math.sqrt(Math.pow(Math.sin( a / 2), 2)
                + Math.cos(radianLatitude1) * Math.cos(radianLatitude2) * Math.pow(Math.sin(b / 2), 2))
        );

        return Math.round(s * EARTH_RADIUS_WGS84);
    }

    /**
     * 判断坐标点是否在多边形范围内。
     * @param point - 目标坐标点（经度, 纬度）
     * @param pts - 多边形各点坐标（经度, 纬度）
     * @return boolean
     * @author: Neo Lia
     */
    public static boolean isInPolygon(Point2D.Double point, List<Point2D.Double> pts) {
        int N = pts.size();
        boolean boundOrVertex = true;
        // 交叉点数量
        int intersectCount = 0;
        // 浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        // 临近顶点
        Point2D.Double p1, p2;
        // 当前点
        Point2D.Double p = point;

        p1 = pts.get(0);
        for (int i = 1; i <= N; ++i) {
            if (p.equals(p1)) {
                return boundOrVertex;
            }

            p2 = pts.get(i % N);
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;
            }

            //射线穿过算法
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                if (p.y <= Math.max(p1.y, p2.y)) {
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
                        return boundOrVertex;
                    }

                    if (p1.y == p2.y) {
                        if (p1.y == p.y) {
                            return boundOrVertex;
                        }
                        else {
                            ++intersectCount;
                        }
                    }
                    else {
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        if (Math.abs(p.y - xinters) < precision) {
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            }
            else {
                if (p.x == p2.x && p.y <= p2.y) {
                    Point2D.Double p3 = pts.get((i + 1) % N);
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    }
                    else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        if (intersectCount % 2 == 0) {
            // 偶数在多边形外
            return false;
        }
        else {
            // 奇数在多边形内
            return true;
        }
    }

    public static void main(String[] args) {
        double x1 = 116.368904, y1 = 39.923423;
        double x2 = 116.387271, y2 = 39.922501;
        System.out.println(calculateWgs84Distance(x1, y1, x2, y2));
    }
}
