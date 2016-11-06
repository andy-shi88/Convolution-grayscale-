package constants;

public class fixedFilters {
    public static final double[] CUSTOM_FILTER = {-0.5, -1, -0.5, 
                                                   -1, 5, -1,
                                                   0, -1, 0};
    public static final double[] EDGE_LINEAR_VERTICAL_PREWITT = {-1, 0, 1, 
                                                                    -1, 0, 1, 
                                                                 -1, 0, 1};
    public static final double[] EDGE_LINEAR_HORIZONTAL_PREWITT = {1, 1, 1, 
                                                                    0, 0, 0,
                                                                      -1, -1, -1};
    public static final double[] BLUR_3_x_3 = {0.0625, 0.125, 0.0625, 
                                               0.125, 0.25, 0.125,
                                               0.00625, 0.125, 0.0625};
    public static final double[] BLUR_5_x_5 = {0, 0, 0, 0, 0, 
                                          0, 1, 1, 1, 0,
                                         0, 1, 1, 1, 0,
                                           0, 1, 1, 1, 0,
                                           0, 0, 0, 0, 0};
    public static final double[] SHARPEN = {0, 0, 0, 0, 0, 
                                          0, 0, -1, 0, 0,
                                            0, -1, 5, -1, 0,
                                           0, 0, -1, 0, 0,
                                           0, 0, 0, 0, 0};
}
