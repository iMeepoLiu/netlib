/*
 * Copyright (c) Ludovic Henry. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Ludovic Henry designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Ludovic Henry in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact hi@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.arpack;

import dev.ludovic.netlib.ARPACK;

public final class NetlibNativeARPACK extends NetlibWrapper {

  private static final NetlibNativeARPACK instance;

  static {
    com.github.fommil.netlib.ARPACK arpack = com.github.fommil.netlib.ARPACK.getInstance();
    if (arpack instanceof com.github.fommil.netlib.F2jARPACK) {
        throw new RuntimeException("Unable to load native implementation");
    }
    instance = new NetlibNativeARPACK(arpack);
  }

  protected NetlibNativeARPACK(com.github.fommil.netlib.ARPACK _arpack) {
    super(_arpack);
  }

  public static ARPACK getInstance() {
    return instance;
  }
}