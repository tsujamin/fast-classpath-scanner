/*
 * This file is part of FastClasspathScanner.
 * 
 * Author: Luke Hutchison <luke .dot. hutch .at. gmail .dot. com>
 * 
 * Hosted at: https://github.com/lukehutch/fast-classpath-scanner
 * 
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Luke Hutchison
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

import static org.junit.Assert.assertTrue;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.whitelisted.Cls;
import io.github.lukehutch.fastclasspathscanner.whitelisted.blacklisted.Blacklisted;

import java.util.Set;

import org.junit.Test;

public class DefaultPackageTest {
    private static final String WHITELIST_PACKAGE = Cls.class.getPackage().getName();

    @Test
    public void scan() throws Exception {
        final Set<String> allClasses = new FastClasspathScanner().scan().getNamesOfAllClasses();
        assertTrue(allClasses.contains(Cls.class.getName()));
        assertTrue(allClasses.contains(FastClasspathScanner.class.getName()));
        assertTrue(allClasses.contains(DefaultPackageTest.class.getName()));
        assertTrue(!allClasses.contains(String.class.getName()));
        assertTrue(allClasses.contains(Object.class.getName()));
        assertTrue(allClasses.contains(Blacklisted.class.getName()));
        assertTrue(allClasses.contains(ClassInDefaultPackage.class.getName()));        
    }

    @Test
    public void scanWithWhitelist() throws Exception {
        final Set<String> allClasses = new FastClasspathScanner(WHITELIST_PACKAGE).scan().getNamesOfAllClasses();
        assertTrue(allClasses.contains(Cls.class.getName()));
        assertTrue(!allClasses.contains(FastClasspathScanner.class.getName()));
        assertTrue(!allClasses.contains(DefaultPackageTest.class.getName()));
        assertTrue(!allClasses.contains(String.class.getName()));
        assertTrue(allClasses.contains(Object.class.getName()));
        assertTrue(allClasses.contains(Blacklisted.class.getName()));
        assertTrue(!allClasses.contains(ClassInDefaultPackage.class.getName()));        
    }
}
