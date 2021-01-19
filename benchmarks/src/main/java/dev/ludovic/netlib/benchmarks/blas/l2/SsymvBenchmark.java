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

package dev.ludovic.netlib.benchmarks.blas.l2;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class SsymvBenchmark extends BLASBenchmark {

    @Param({"U", "L"})
    public String uplo;

    @Param({"10", "1000"})
    public int n;

    public float alpha;
    public float[] a;
    public float[] x;
    public float beta;
    public float[] y, yclone;

    @Setup(Level.Trial)
    public void setup() {
        alpha = randomFloat();
        a = randomFloatArray(n * n);
        x = randomFloatArray(n);
        beta = randomFloat();
        y = randomFloatArray(n);
    }

    @Setup(Level.Invocation)
    public void setupIteration() {
        yclone = y.clone();
    }

    @Benchmark
    public void blas(Blackhole bh) {
        blas.ssymv(uplo, n, alpha, a, n, x, 1, beta, yclone, 1);
        bh.consume(yclone);
    }
}
