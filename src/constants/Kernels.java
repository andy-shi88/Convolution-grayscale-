package constants;

public class Kernels {
    private double[] kernels;
    private String name;
    
    public Kernels(String name, double[] kernels) {
        this.name = name;
        this.kernels = kernels;
    }
    @Override
    public String toString() {
        return this.name;
    }
    public double[] getKernels() {
        return kernels;
    }

    public void setKernels(double[] kernels) {
        this.kernels = kernels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
