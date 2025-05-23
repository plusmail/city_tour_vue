export function getSubRelation(xml) {

    let forward = {
        mainLine : [] ,
        extendLine : []
    }

    let backward = {
        mainLine : [] ,
        extendLine : []
    };

    let result = {
        forwardRoute : forward,
        backwardRoute: backward
    }

    let rels = xml.getElementsByTagName("relation");
    for (let i = 0; i < rels.length; i++) {
        //Get all members
        let rel = rels[i];
        let members = rel.getElementsByTagName("member");

        for (let j = 0; j < members.length; j++) {
            if (members[j].getAttribute("type") === "relation") {
                switch (members[j].getAttribute("role")) {
                    case "forward":
                        result.forwardRoute.mainLine.push(members[j].getAttribute("ref"));
                        break;
                    case "backward":
                        result.backwardRoute.mainLine.push(members[j].getAttribute("ref"));
                        break;
                    case "forward_extend":
                        result.forwardRoute.extendLine.push(members[j].getAttribute("ref"));
                        break;
                    case "backward_extend":
                        result.backwardRoute.extendLine.push(members[j].getAttribute("ref"));
                        break;
                }
            }
        }
    }
    
    return result;
}

export function getProxyData(xml) {
    let features = [],
        nodes = getNodes(xml),
        ways = getWays(xml, nodes)

    for (let node_id in nodes) {
        let node = nodes[node_id];
        if (isBusStop(node)) {
            features.push(node);
        }
    }

    for (let i = 0; i < ways.length; i++) {
        let way = ways[i];
        features.push(way);
    }

    return features;
}

export function getNodes(xml) {
    let result = {};

    let nodes = xml.getElementsByTagName("node");
    for (let i = 0; i < nodes.length; i++) {
        let node = nodes[i],
            id = node.getAttribute("id");
        result[id] = {
            id: id,
            type: "node",
            latLng: [node.getAttribute("lat"),node.getAttribute("lon")],
            tags: getTags(node)
        };
    }

    return result;
}

export function getWays(xml, nodes) {
    let result = [];

    let ways = xml.getElementsByTagName("way");
    for (let i = 0; i < ways.length; i++) {
        let way = ways[i],
            nds = way.getElementsByTagName("nd");

        let way_object = {
            id: way.getAttribute("id"),
            type: "way",
            nodes: new Array(nds.length),
            tags: getTags(way)
        };

        for (let j = 0; j < nds.length; j++) {
            way_object.nodes[j] = nodes[nds[j].getAttribute("ref")];
        }

        result.push(way_object);
    }

    return result;
}

export function getTags(xml) {
    let result = {};

    let tags = xml.getElementsByTagName("tag");
    for (let j = 0; j < tags.length; j++) {
        result[tags[j].getAttribute("k")] = tags[j].getAttribute("v");
    }

    return result;
}

export function isBusStop(node) {
    let bIsBusStop = false;

    for (let key in node.tags) {
        if (node.tags[key] == "bus_stop") {
            bIsBusStop = true;
            break;
        }
    }

    return bIsBusStop;
}