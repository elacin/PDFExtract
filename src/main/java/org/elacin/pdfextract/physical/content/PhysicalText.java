/*
 * Copyright 2010 Øyvind Berg (elacin@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.elacin.pdfextract.physical.content;

import org.elacin.pdfextract.StyledText;
import org.elacin.pdfextract.style.Style;
import org.elacin.pdfextract.util.Rectangle;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA. User: elacin Date: Sep 23, 2010 Time: 2:36:44 PM To change this
 * template use File | Settings | File Templates.
 */
public class PhysicalText extends AssignablePhysicalContent implements StyledText {
// ------------------------------ FIELDS ------------------------------

public       float  charSpacing;
public final String text;
public final int    rotation;

// --------------------------- CONSTRUCTORS ---------------------------

public PhysicalText(final String text,
                    final Style style,
                    final float x,
                    final float y,
                    final float width,
                    final float height,
                    final int rotation) {
    this(text, style, new Rectangle(x, y, width, height), rotation);
}

PhysicalText(final String text,
             final Style style,
             final Rectangle position,
             final int rotation) {
    super(position, style);
    this.text = text;
    this.rotation = rotation;
}

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface StyledText ---------------------

public String getText() {
    return text;
}

// ------------------------ CANONICAL METHODS ------------------------

@Override
public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Text");
    sb.append("{'").append(text).append('\'');
    sb.append(", style=").append(style);
    sb.append(", pos=").append(pos);
    sb.append(", charSpacing=").append(charSpacing);
    sb.append('}');
    return sb.toString();
}

// ------------------------ OVERRIDING METHODS ------------------------

@NotNull
@Override
public PhysicalText getPhysicalText() {
    return this;
}

@Override
public boolean isText() {
    return true;
}

// --------------------- GETTER / SETTER METHODS ---------------------


public int getRotation() {
    return rotation;
}

// -------------------------- PUBLIC METHODS --------------------------

@NotNull
public PhysicalText combineWith(@NotNull final PhysicalText next) {
    return new PhysicalText(text + next.text, style, pos.union(next.pos), rotation);
}

public float getAverageCharacterWidth() {
    return getPos().getWidth() / (float) text.length();
}

public boolean isSameStyleAs(@NotNull final PhysicalText next) {
    return getStyle().equals(next.getStyle());
}
}
