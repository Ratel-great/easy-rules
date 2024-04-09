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

import org.jeasy.rules.core.RuleProxy;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * 这个类封装了一组规则并表示一个规则命名空间。
 * 在规则命名空间中，规则必须有唯一的名称。
 * <p>
 * 规则将基于{@link Rule#compareTo(Object)}来相互比较
 * 方法，所以{@link Rule}的实现被期望正确实现
 * {@code compareTo}确保在单个命名空间中唯一的规则名称
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class Rules implements Iterable<Rule> {

    private Set<Rule> rules = new TreeSet<>();

    /**
     * 创建一个新的{@link Rules}对象。
     *
     * @param rules to register
     */
    public Rules(Set<Rule> rules) {
        this.rules = new TreeSet<>(rules);
    }

    /**
     * 创建一个新的{@link Rules}对象。
     *
     * @param rules to register
     */
    public Rules(Rule... rules) {
        Collections.addAll(this.rules, rules);
    }

    /**
     * 创建一个新的{@link Rules}对象。
     *
     * @param rules to register
     */
    public Rules(Object... rules) {
        this.register(rules);
    }

    /**
     * 注册一个或多个新规则
     *
     * @param rules to register, must not be null
     */
    public void register(Object... rules) {
        Objects.requireNonNull(rules);
        for (Object rule : rules) {
            Objects.requireNonNull(rule);
            this.rules.add(RuleProxy.asRule(rule));
        }
    }

    /**
     * 取消注册一个或多个规则。
     *
     * @param rules to unregister, must not be null
     */
    public void unregister(Object... rules) {
        Objects.requireNonNull(rules);
        for (Object rule : rules) {
            Objects.requireNonNull(rule);
            this.rules.remove(RuleProxy.asRule(rule));
        }
    }

    /**
     * 按名称取消注册规则。
     *
     * @param ruleName name of the rule to unregister, must not be null
     */
    public void unregister(final String ruleName) {
        Objects.requireNonNull(ruleName);
        Rule rule = findRuleByName(ruleName);
        if (rule != null) {
            unregister(rule);
        }
    }

    /**
     * 检查规则集是否为空。
     *
     * @return true if the rule set is empty, false otherwise
     */
    public boolean isEmpty() {
        return rules.isEmpty();
    }

    /**
     * 清空规则
     */
    public void clear() {
        rules.clear();
    }

    /**
     * 返回当前注册了多少条规则。
     *
     * @return the number of rules currently registered
     */
    public int size() {
        return rules.size();
    }

    /**
     * 返回规则集上的迭代器。它并不打算删除规则
     * *使用此迭代器。
     *
     * @return an iterator on the rules set
     */
    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

    private Rule findRuleByName(String ruleName) {
        return rules.stream()
                .filter(rule -> rule.getName().equalsIgnoreCase(ruleName))
                .findFirst()
                .orElse(null);
    }
}
