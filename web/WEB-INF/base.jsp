<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Medimenu</title>
      <style>
      @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro');
      body{background-image: url("<%= request.getContextPath()%>/img/back.jpg");background-attachment:fixed;background-size: cover;margin:0;text-align: center;}
      section{margin-left: 3%; padding:5%; min-height: 100vh; font-family: 'Source Sans Pro', sans-serif; background-color: whitesmoke;overflow: scroll;}
      h1{font-size: 40px; font-weight: 700;}
      fieldset{margin-bottom: 20px;background-color: white;padding: 20px;}
      button{padding:20px; width: 100%;}
      legend{background-color: lightgray; padding:5px;}
    </style>
    <%-- D3--%>
      <script src="https://d3js.org/d3-hierarchy.v1.min.js"></script>
      <style>
          body{margin:50px;}
          .node {
              cursor: pointer;
          }

          .node circle {
              fill: #fff;
              stroke: steelblue;
              stroke-width: 3px;
          }

          .node text {
              font: 12px sans-serif;
              color: black;
          }

          .link {
              fill: none;
              stroke: #ccc;
              stroke-width: 2px;
          }

      </style>
  </head>

  <body>
  <section>
    <h1>Représentation graphique de la base de données</h1>

  </section>
  <!-- load the d3.js library -->
  <script src="http://d3js.org/d3.v3.min.js"></script>

  <script>

      var treeData = [
          {
              'name':'Base de données',
              'parent': 'null',
              'children': [
                  {
                      'name':'fruits. légumes. légumineuses et oléagineux',
                      'parent': 'Base de donnée',
                      'children': [
                          {
                              'name':'fruits',
                              'parent': 'fruits. légumes. légumineuses et oléagineux',
                              'children': [
                                  {
                                      'name':'fruits crus',
                                      'parent': 'fruits'
                                  },
                                  {
                                      'name':'compotes et assimilés',
                                      'parent': 'fruits'
                                  },
                                  {
                                      'name':'fruits appertisés',
                                      'parent': 'fruits'
                                  },
                                  {
                                      'name':'fruits séchés',
                                      'parent': 'fruits'
                                  },
                              ]},
                          {
                              'name':'fruits à coque et graines oléagineuses',
                              'parent': 'fruits. légumes. légumineuses et oléagineux',
                              'children': [
                              ]},
                          {
                              'name':'pommes de terre et autres tubercules',
                              'parent': 'fruits. légumes. légumineuses et oléagineux',
                              'children': [
                              ]},
                          {
                              'name':'légumes',
                              'parent': 'fruits. légumes. légumineuses et oléagineux',
                              'children': [
                                  {
                                      'name':'légumes crus',
                                      'parent': 'légumes'
                                  },
                                  {
                                      'name':'légumes cuits',
                                      'parent': 'légumes'
                                  },
                                  {
                                      'name':'légumes séchés ou déshydratés',
                                      'parent': 'légumes'
                                  },
                              ]},
                          {
                              'name':'légumineuses',
                              'parent': 'fruits. légumes. légumineuses et oléagineux',
                              'children': [
                                  {
                                      'name':'légumineuses cuites',
                                      'parent': 'légumineuses'
                                  },
                                  {
                                      'name':'légumineuses fraîches',
                                      'parent': 'légumineuses'
                                  },
                                  {
                                      'name':'légumineuses sèches',
                                      'parent': 'légumineuses'
                                  },
                              ]},
                      ]},

                  {
                      'name':'viandes. œufs. poissons',
                      'parent': 'Base de donnée',
                      'children': [
                          {
                              'name':'mollusques et crustacés crus',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                          {
                              'name':'poissons crus',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                          {
                              'name':'charcuteries',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                                  {
                                      'name':'jambons cuits',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'jambons secs et crus',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'saucisson secs',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'saucisses et assimilés',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'pâtés et terrines',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'rillettes',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'quenelles',
                                      'parent': 'charcuteries'
                                  },
                                  {
                                      'name':'autres spécialités charcutières',
                                      'parent': 'charcuteries'
                                  },
                              ]},
                          {
                              'name':'viandes cuites',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                                  {
                                      'name':'bœuf et veau',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'porc',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'poulet',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'dinde',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'agneau et mouton',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'gibier',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'autres viandes',
                                      'parent': 'viandes cuites'
                                  },
                                  {
                                      'name':'abats',
                                      'parent': 'viandes cuites'
                                  },
                              ]},
                          {
                              'name':'œufs',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                                  {
                                      'name':'œufs cuits',
                                      'parent': 'œufs'
                                  },
                                  {
                                      'name':'œufs crus',
                                      'parent': 'œufs'
                                  },
                                  {
                                      'name':'omelettes et autres ovoproduits',
                                      'parent': 'œufs'
                                  },
                              ]},
                          {
                              'name':'viandes crues',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                                  {
                                      'name':'bœuf et veau',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'porc',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'poulet',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'dinde',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'agneau et mouton',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'gibier',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'autres viandes',
                                      'parent': 'viandes crues'
                                  },
                                  {
                                      'name':'abats',
                                      'parent': 'viandes crues'
                                  },
                              ]},
                          {
                              'name':'autres produits à base de viande',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                          {
                              'name':'poissons cuits',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                          {
                              'name':'produits à base de poissons et produits de la mer',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                          {
                              'name':'mollusques et crustacés cuits',
                              'parent': 'viandes. œufs. poissons',
                              'children': [
                              ]},
                      ]},
                  {
                      'name':'lait et produits laitiers',
                      'parent': 'Base de donnée',
                      'children': [
                          {
                              'name':'produits laitiers frais et assimilés',
                              'parent': 'lait et produits laitiers',
                              'children': [
                                  {
                                      'name':'yaourts et spécialités laitières type yaourt',
                                      'parent': 'produits laitiers frais et assimilés'
                                  },
                                  {
                                      'name':'fromages blancs',
                                      'parent': 'produits laitiers frais et assimilés'
                                  },
                                  {
                                      'name':'desserts lactés',
                                      'parent': 'produits laitiers frais et assimilés'
                                  },
                                  {
                                      'name':'autres desserts',
                                      'parent': 'produits laitiers frais et assimilés'
                                  },
                              ]},
                          {
                              'name':'laits',
                              'parent': 'lait et produits laitiers',
                              'children': [
                                  {
                                      'name':'laits de vaches liquides (non concentrés)',
                                      'parent': 'laits'
                                  },
                                  {
                                      'name':'laits autres que de vache',
                                      'parent': 'laits'
                                  },
                                  {
                                      'name':'laits de vache concentrés ou en poudre',
                                      'parent': 'laits'
                                  },
                              ]},
                      ]},
                  {
                      'name':'produits céréaliers',
                      'parent': 'Base de donnée',
                      'children': [
                          {
                              'name':'gâteaux et pâtisseries',
                              'parent': 'produits céréaliers',
                              'children': [
                              ]},
                          {
                              'name':'pâtes. riz et céréales',
                              'parent': 'produits céréaliers',
                              'children': [
                                  {
                                      'name':'pâtes. riz et céréales cuits',
                                      'parent': 'pâtes. riz et céréales'
                                  },
                                  {
                                      'name':'pâtes. riz et céréales crus',
                                      'parent': 'pâtes. riz et céréales'
                                  },
                              ]},
                          {
                              'name':'farines et pâtes à tarte',
                              'parent': 'produits céréaliers',
                              'children': [
                                  {
                                      'name':'farines',
                                      'parent': 'farines et pâtes à tarte'
                                  },
                                  {
                                      'name':'pâtes à tarte',
                                      'parent': 'farines et pâtes à tarte'
                                  },
                              ]},
                          {
                              'name':'pains et viennoiseries',
                              'parent': 'produits céréaliers',
                              'children': [
                                  {
                                      'name':'pains',
                                      'parent': 'pains et viennoiseries'
                                  },
                                  {
                                      'name':'biscottes et pains grillés',
                                      'parent': 'pains et viennoiseries'
                                  },
                                  {
                                      'name':'viennoiseries',
                                      'parent': 'pains et viennoiseries'
                                  },
                              ]},
                          {
                              'name':'céréales de petit-déjeuner et biscuits',
                              'parent': 'produits céréaliers',
                              'children': [
                                  {
                                      'name':'céréales de petit-déjeuner',
                                      'parent': 'céréales de petit-déjeuner et biscuits'
                                  },
                                  {
                                      'name':'barres céréalières',
                                      'parent': 'céréales de petit-déjeuner et biscuits'
                                  },
                                  {
                                      'name':'biscuits sucrés',
                                      'parent': 'céréales de petit-déjeuner et biscuits'
                                  },
                                  {
                                      'name':'biscuits apéritifs',
                                      'parent': 'céréales de petit-déjeuner et biscuits'
                                  },
                              ]},
                      ]},
                  {
                      'name':'entrées et plats composés',
                      'parent': 'Base de donnée',
                      'children': [
                          {
                              'name':'salades composées et crudités',
                              'parent': 'entrées et plats composés',
                              'children': [
                              ]},
                          {
                              'name':'feuilletées et autres entrées',
                              'parent': 'entrées et plats composés',
                              'children': [
                              ]},
                          {
                              'name':'soupes',
                              'parent': 'entrées et plats composés',
                              'children': [
                              ]},
                          {
                              'name':'pizzas. tartes et crêpes salées',
                              'parent': 'entrées et plats composés',
                              'children': [
                              ]},
                          {
                              'name':'sandwichs',
                              'parent': 'entrées et plats composés',
                              'children': [
                              ]},
                          {
                              'name':'plats composés',
                              'parent': 'entrées et plats composés',
                              'children': [
                                  {
                                      'name':'plats de viande sans garniture',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de viande et féculents',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de viande et légumes/légumineuses',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de poisson sans garniture',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de poisson et féculents',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de légumes/légumineuses',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de céréales/pâtes',
                                      'parent': 'plats composés'
                                  },
                                  {
                                      'name':'plats de fromage',
                                      'parent': 'plats composés'
                                  },
                              ]},
                      ]},]
          }

      ];


      // ************** Generate the tree diagram	 *****************
      var margin = {top: 50, right: 50, bottom: 50, left: 50},
          width = 1200 - margin.right - margin.left,
          height = 2000 - margin.top - margin.bottom;

      var i = 0,
          duration = 750,
          root;

      var tree = d3.layout.tree()
          .size([height, width]);

      var diagonal = d3.svg.diagonal()
          .projection(function(d) { return [d.y, d.x]; });

      var svg = d3.select("section").append("svg")
          .attr("width", width + margin.right + margin.left)
          .attr("height", height + margin.top + margin.bottom)
          .append("g")
          .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

      root = treeData[0];
      root.x0 = height / 2;
      root.y0 = 0;

      update(root);

      d3.select(self.frameElement).style("height", "500px");

      function update(source) {

          // Compute the new tree layout.
          var nodes = tree.nodes(root).reverse(),
              links = tree.links(nodes);

          // Normalize for fixed-depth.
          nodes.forEach(function(d) { d.y = d.depth * 300; });

          // Update the nodes…
          var node = svg.selectAll("g.node")
              .data(nodes, function(d) { return d.id || (d.id = ++i); });

          // Enter any new nodes at the parent's previous position.
          var nodeEnter = node.enter().append("g")
              .attr("class", "node")
              .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
              .attr("fill", "white")
              .on("click", click);

          nodeEnter.append("circle")
              .attr("r", 1e-6)
              .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });


          nodeEnter.append("text")
              .attr("x", function(d) { return d.children || d._children ? 20 : 20; })
              .attr("dy", ".40em")
              .attr("fill", "black")
                  .attr("text-anchor", function(d) { return d.children || d._children ? "start" : "start"; })
              .html(function(d) { return d.name; })
              .style("fill-opacity", 1e-6);

          // Transition nodes to their new position.
          var nodeUpdate = node.transition()
              .duration(duration)
              .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

          nodeUpdate.select("circle")
              .attr("r", 10)
              .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

          nodeUpdate.select("text")
              .style("fill-opacity", 1);

          // Transition exiting nodes to the parent's new position.
          var nodeExit = node.exit().transition()
              .duration(duration)
              .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
              .remove();

          nodeExit.select("circle")
              .attr("r", 1e-6);

          nodeExit.select("text")
              .style("fill-opacity", 1e-6);

          // Update the links…
          var link = svg.selectAll("path.link")
              .data(links, function(d) { return d.target.id; });

          // Enter any new links at the parent's previous position.
          link.enter().insert("path", "g")
              .attr("class", "link")
              .attr("d", function(d) {
                  var o = {x: source.x0, y: source.y0};
                  return diagonal({source: o, target: o});
              });

          // Transition links to their new position.
          link.transition()
              .duration(duration)
              .attr("d", diagonal);

          // Transition exiting nodes to the parent's new position.
          link.exit().transition()
              .duration(duration)
              .attr("d", function(d) {
                  var o = {x: source.x, y: source.y};
                  return diagonal({source: o, target: o});
              })
              .remove();

          // Stash the old positions for transition.
          nodes.forEach(function(d) {
              d.x0 = d.x;
              d.y0 = d.y;
          });
      }

      // Toggle children on click.
      function click(d) {
          if (d.children) {
              d._children = d.children;
              d.children = null;
          } else {
              d.children = d._children;
              d._children = null;
          }
          update(d);
      }

  </script>

  </body>
</html>
