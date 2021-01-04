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

import dev.ludovic.blas.BLAS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class DdotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int n = 9;
        double[] x = new double[] { 1.0, 0.0, -2.0, 3.0, 1.0, 0.0, -2.0, 3.0, 3.0 };
        double[] y = new double[] { 2.0, 1.0,  0.0, 0.0, 2.0, 1.0,  0.0, 0.0, 0.0 };

        assertEquals(4.0, blas.ddot(n, x, 1, y, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        int n = 5;
        double[] x = new double[] { 0.0, 1.0 };
        double[] y = new double[] { 0.0, 1.0 };

        assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> {
            blas.ddot(n, x, 1, y, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBoundBecauseOfOffset(BLAS blas) {
        int n = 9;
        double[] x = new double[] { 1.0, 0.0, -2.0, 3.0, 1.0, 0.0, -2.0, 3.0, 3.0 };
        int offsetx = 1;
        double[] y = new double[] { 2.0, 1.0,  0.0, 0.0, 2.0, 1.0,  0.0, 0.0, 0.0 };
        int offsety = 1;

        assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> {
            blas.ddot(n, x, offsetx, 1, y, offsety, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBoundOnlyForX(BLAS blas) {
        int n = 5;
        double[] x = new double[] { 1.0, 0.0, -2.0, 3.0, 1.0, 0.0, -2.0, 3.0, 3.0 };
        double[] y = new double[] { 2.0, 1.0,  0.0, 0.0, 2.0, 1.0,  0.0, 0.0, 0.0 };

        assertEquals(6.0, blas.ddot(n, x, 2, y, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testXAndYAreNullAndNIsZero(BLAS blas) {
        int n = 0;
        double[] x = null;
        double[] y = null;

        assertEquals(0.0, blas.ddot(n, x, 1, y, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testXAndYAreNullAndNIsOne(BLAS blas) {
        int n = 1;
        double[] x = null;
        double[] y = null;

        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.ddot(n, x, 1, y, 1);
        });
    }
}