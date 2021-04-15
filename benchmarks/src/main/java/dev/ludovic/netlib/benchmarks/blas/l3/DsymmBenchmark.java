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

package dev.ludovic.netlib.benchmarks.blas.l3;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class DsymmBenchmark extends L3Benchmark {

    @Param({"L", "R"})
    public String side;
    @Param({"U", "L"})
    public String uplo;

    @Param({"10", "1000"})
    public int m;
    @Param({"10", "1000"})
    public int n;

    public double alpha;
    public double[] a;
    public int lda;
    public double[] b;
    public int ldb;
    public double beta;
    public double[] c, cclone;
    public int ldc;

    @Setup(Level.Trial)
    public void setup() {
        alpha = randomDouble();
        a = randomDoubleArray(side.equals("L") ? m * m : n * n);
        b = randomDoubleArray(m * n);
        beta = randomDouble();
        c = randomDoubleArray(m * n);
    }

    @Setup(Level.Invocation)
    public void setupIteration() {
        cclone = c.clone();
    }

    @Benchmark
    public void blas(Blackhole bh) {
        blas.dsymm(side, uplo, m, n, alpha, a, side.equals("L") ? m : n, b, m, beta, cclone, m);
        bh.consume(cclone);
    }
}
