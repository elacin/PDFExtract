/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elacin.pdfextract.pdfbox;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.TextPosition;
import org.elacin.pdfextract.physical.content.HasPosition;
import org.elacin.pdfextract.util.Rectangle;
import org.jetbrains.annotations.NotNull;

/**
 * This represents a string and a position on the screen of those characters.
 *
 * @author <a href="mailto:ben@benlitchfield.com">Ben Litchfield</a>
 * @version $Revision: 1.12 $
 */
public class ETextPosition extends TextPosition implements HasPosition {
// ------------------------------ FIELDS ------------------------------

private static final Logger log = Logger.getLogger(ETextPosition.class);
@NotNull
private Rectangle pos;
private float     baseLine;

// --------------------------- CONSTRUCTORS ---------------------------

public ETextPosition(final PDPage page,
                     final Matrix textPositionSt,
                     final Matrix textPositionEnd,
                     final float maxFontH,
                     @NotNull final float[] individualWidths,
                     final float spaceWidth,
                     @NotNull final String string,
                     final PDFont currentFont,
                     final float fontSizeValue,
                     final int fontSizeInPt,
                     final float ws) {
    super(page, textPositionSt, textPositionEnd, maxFontH, individualWidths, spaceWidth, string,
            currentFont, fontSizeValue, fontSizeInPt, ws);

    float x = getX();
    float y = getY();
    float w = getWidth();
    float h = getHeight();

    if (h <= 0.0f && w < 0.0f) {
        throw new IllegalArgumentException("Passed text '" + string + "' with no size.");
    }

    if (h <= 0.0f) {
        h = getWidth() / (float) string.length();
        h *= 1.5;

        if (log.isDebugEnabled()) {
            log.debug(String.format("LOG00630:Guessing height of text %s at (%s,%s). height = %f",
                    string, x, y, h));
        }
    }

    if (w <= 0.0f) {
        w = getHeight() / 2.0f;
        if (log.isDebugEnabled()) {
            log.debug(String.format("LOG00630:Guessing width of text %s at (%s,%s). height = %f",
                    string, x, y, w));
        }
    }

    pos = new Rectangle(x, y, w, h);
}

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface HasPosition ---------------------

@NotNull
public Rectangle getPos() {
    return pos;
}

// --------------------- GETTER / SETTER METHODS ---------------------

public float getBaseLine() {
    return baseLine;
}

public void setBaseLine(final float baseLine) {
    this.baseLine = baseLine;
}


public void setPos(@NotNull final Rectangle pos) {
    this.pos = pos;
}
}