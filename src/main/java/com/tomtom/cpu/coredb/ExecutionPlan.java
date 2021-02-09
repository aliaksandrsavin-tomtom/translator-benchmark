package com.tomtom.cpu.coredb;

import com.teleatlas.global.common.ddct.DictionaryFeature;
import com.tomtom.cpu.api.geometry.Coordinate;
import com.tomtom.cpu.api.geometry.LineString;
import com.tomtom.cpu.coredb.translator4.system.test.framework.TestBeans;
import com.tomtom.cpu.coredb.translator4.system.test.framework.TestBeansBuilder;
import com.tomtom.cpu.coredb.writeapi.logicaltransactions.LogicalTransaction;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;

import static com.tomtom.cpu.coredb.translator4.handler.AbstractHandlerTest.getModel;
import static com.tomtom.cpu.coredb.translator4.handler.TestLogicalTransactionBuilder.logicalTransactionBuilder;

@State(Scope.Benchmark)
public class ExecutionPlan {

    private final Coordinate coord1 = new Coordinate(15000000, 425000000);
    private final Coordinate coord2 = new Coordinate(16000000, 426000000);

    private TestBeans testBeans;
    private LogicalTransaction logicalTransaction;

    public TestBeans getTestBeans() {
        return testBeans;
    }

    public LogicalTransaction getLogicalTransaction() {
        return logicalTransaction;
    }

    @Setup(Level.Iteration)
    public void setUp() {
        testBeans = new TestBeansBuilder().withModel(getModel()).build();

        final DictionaryFeature featureType = getModel().TTOM_Topology.FEATURES.Edge;
        final LineString newGeometry = new LineString(Arrays.asList(coord1, coord2));
        logicalTransaction = logicalTransactionBuilder().addEdit().createGeometry(newGeometry, featureType).build();
    }
}
