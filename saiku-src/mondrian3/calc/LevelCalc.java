/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2007 Pentaho
// All Rights Reserved.
*/
package mondrian3.calc;

import mondrian3.olap.Evaluator;
import mondrian3.olap.Level;

/**
 * Expression which yields a {@link mondrian3.olap.Level}.
 *
 * <p>When implementing this interface, it is convenient to extend
 * {@link mondrian3.calc.impl.AbstractLevelCalc}, but it is not required.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public interface LevelCalc extends Calc {
    /**
     * Evaluates this expression to yield a level.
     *
     * <p>Never returns null.
     *
     * @param evaluator Evaluation context
     * @return a level
     */
    Level evaluateLevel(Evaluator evaluator);
}

// End LevelCalc.java
