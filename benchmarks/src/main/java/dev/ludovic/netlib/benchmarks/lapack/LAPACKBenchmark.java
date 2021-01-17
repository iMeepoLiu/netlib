/*
 * Copyright 2020, Ludovic Henry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.ludovic.netlib.benchmarks.lapack;

import dev.ludovic.netlib.LAPACK;

import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public abstract class LAPACKBenchmark {

    public LAPACK lapack;

    @Param({"f2j", "native", "java"})
    public String implementation;

    @Setup
    public void setupImplementation() {
        switch (implementation) {
        case "f2j":
            lapack = dev.ludovic.netlib.lapack.NetlibWrapper.wrap(new com.github.fommil.netlib.F2jLAPACK());
            break;
        case "native":
            lapack = dev.ludovic.netlib.lapack.NetlibWrapper.wrap(com.github.fommil.netlib.LAPACK.getInstance());
            break;
        case "java":
            lapack = dev.ludovic.netlib.lapack.JavaLAPACK.getInstance();
            break;
        default: throw new IllegalArgumentException("Unknown implementation = " + implementation);
        }
    }

    private final Random rand = new Random(0);

    protected double randomDouble() {
        return rand.nextDouble();
    }

    protected double[] randomDoubleArray(int n) {
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = rand.nextDouble();
        }
        return res;
    }

    protected float randomFloat() {
        return rand.nextFloat();
    }

    protected float[] randomFloatArray(int n) {
        float[] res = new float[n];
        for (int i = 0; i < n; i++) {
            res[i] = rand.nextFloat();
        }
        return res;
    }
}
