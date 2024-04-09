/*
 * The MIT License
 *
 *  Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.jeasy.rules.api;

/**
 * 可以由规则引擎触发的规则的抽象。
 * <p>
 * 规则注册在{@link Rules}类型规则的命名空间中
 * 其中必须有一个<strong>唯一的</strong>名称。
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public interface Rule extends Comparable<Rule> {

    /**
     * Default rule name.
     */
    String DEFAULT_NAME = "rule";

    /**
     * Default rule description.
     */
    String DEFAULT_DESCRIPTION = "description";

    /**
     * Default rule priority.
     */
    int DEFAULT_PRIORITY = Integer.MAX_VALUE - 1;

    /**
     * 规则名
     *
     * @return the rule name
     */
    default String getName() {
        return DEFAULT_NAME;
    }

    /**
     * 规则描述
     *
     * @return rule description
     */
    default String getDescription() {
        return DEFAULT_DESCRIPTION;
    }

    /**
     * 规则优先级
     *
     * @return rule priority
     */
    default int getPriority() {
        return DEFAULT_PRIORITY;
    }

    /**
     * 这个方法实现了规则的条件。
     * <strong>实现应该处理任何运行时异常并相应地返回true/false </strong>
     *
     * @return true if the rule should be applied given the provided facts, false otherwise
     */
    boolean evaluate(Facts facts);

    /**
     * 这个方法实现了规则的操作。
     *
     * @throws Exception thrown if an exception occurs when performing action(s)
     */
    void execute(Facts facts) throws Exception;

}
