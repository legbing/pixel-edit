package com.ooad.pixeledit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.awt.image.BufferedImage;

@Getter
@Setter
@Entity
public class Filter {

    @Column(nullable=false)
    private String filterType;

    @Column(nullable=false, unique=true)
    private String filterName;
    private static double[][] kernel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filter_id;

    public Filter() {
    }

    public Filter(String filterType, String filterName) {
        this.filterType = filterType;
        this.filterName = filterName;
    }

    public String getFilter_type() {
        return filterType;
    }

    public void setFilter_type(String filter_type) {
        this.filterType = filter_type;
    }

    public String getFilter_name() {
        return filterName;
    }

    public void setFilter_name(String filter_name) {
        this.filterName = filter_name;
    }

    public void setKernel(int k1, int k2, int k3, int k4, int k5, int k6, int k7, int k8, int k9) {
        kernel = new double[][]{{k1, k2, k3}, {k4, k5, k6}, {k7, k8, k9}};
        Filter filter = createFilter(filterType, filterName);
    }

    public Filter createFilter(String filter_type, String filter_name) {
        if(filter_type == null)
        {
            return null;
        }

        if(filter_type.equalsIgnoreCase("Edge")) {
            return Edge.getInstance(filter_name, kernel);
        }
        else if(filter_type.equalsIgnoreCase("Blur")) {
            return Blur.getInstance(filter_name, kernel);
        }
        else if(filter_type.equalsIgnoreCase("Sharpen")) {
            return Sharpen.getInstance(filter_name, kernel);
        }
        
        return null;
    }
    public BufferedImage applyFilter(BufferedImage bufferedImage)
    {
        return null;
    }

}