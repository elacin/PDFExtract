package org.elacin.pdfextract.integration.graphics;

import org.elacin.pdfextract.content.GraphicContent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.List;

/**
 * This represents a surface on which it is possible to draw. <p/> For graphic segmentation purposes
 * no real drawing will occur, but a list of graphic placements will be created
 */
public interface DrawingSurface {

// -------------------------- PUBLIC METHODS --------------------------
void clearSurface();

void drawImage(Image image, AffineTransform at, Shape clippingPath);

void fill(GeneralPath originalPath, Color color, Shape clippingPath);

@NotNull List<GraphicContent> getGraphicContents();

void strokePath(GeneralPath originalPath, Color color, Shape clippingPath);
}
