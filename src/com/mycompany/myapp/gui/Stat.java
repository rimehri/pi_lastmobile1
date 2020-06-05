
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entities.societe;
import java.util.ArrayList;


public class Stat extends Form implements IDemoChart{
    private boolean drawOnMutableImage;
    Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);
    Form stat;
    Homefournisseur h = new Homefournisseur(stat);
    
    public Stat(ArrayList<societe> societies) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->h.show());
       
        this.add(execute(societies));
    }
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(35);
    renderer.setLegendTextSize(40);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        //series.add("Project " + ++k, value);
        series.add("" + ++k, value);
    }

    return series;
}

public Form createPieChartForm(ArrayList<societe> societies) {
    // Generate the values
    double[] values = new double[5];
    for (int i=0; i < 5;i++) {
        values[i] = societies.get(i).getVue();
    }

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(150);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.WHITE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Top Fournisseurs", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;

}

public Form execute(ArrayList<societe> societies) {
    double[] values = new double[5];
    for (int i=0; i < 5;i++) {
        values[i] = societies.get(i).getVue();
    }
    int[] colors = new int[] { ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.GRAY, ColorUtil.CYAN };
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    //renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setBackgroundColor(ColorUtil.WHITE);
    renderer.setApplyBackgroundColor(true);
    renderer.setLabelsColor(ColorUtil.BLACK);
    
    final CategorySeries seriesSet = buildCategoryDataset("Project budget", values);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    comp.getStyle().setBgColor(0xff0000);
    comp.getStyle().setBgTransparency(255);
    
    return wrap("Top Fournisseurs", comp);
    
  }

public void setDrawOnMutableImage(boolean b) {
      this.drawOnMutableImage = b;
  }
  
  public boolean isDrawOnMutableImage() {
      return this.drawOnMutableImage;
  }

protected Form wrap(String title, Component c){
      c.getStyle().setBgColor(0xff0000);
      Form f = new Form(title);
      f.setLayout(new BorderLayout());
      if (isDrawOnMutableImage()) {
          int dispW = Display.getInstance().getDisplayWidth();
          int dispH = Display.getInstance().getDisplayHeight();
          Image img = Image.createImage((int)(dispW * 0.8), (int)(dispH * 0.8), 0x0);
          Graphics g = img.getGraphics();
          c.setWidth((int)(dispW * 0.8));
          c.setHeight((int)(dispH * 0.8));
          c.paint(g);
          f.addComponent(BorderLayout.CENTER, new Label(img));
      } else {
        f.addComponent(BorderLayout.CENTER, c);
      }
      
      return f;
  }

   
}
