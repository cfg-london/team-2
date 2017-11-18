type = ['', 'info', 'success', 'warning', 'danger'];


demo = {
  initPickColor: function() {
    $('.pick-class-label').click(function() {
      var new_class = $(this).attr('new-class');
      var old_class = $('#display-buttons').attr('data-class');
      var display_div = $('#display-buttons');
      if (display_div.length) {
        var display_buttons = display_div.find('.btn');
        display_buttons.removeClass(old_class);
        display_buttons.addClass(new_class);
        display_div.attr('data-class', new_class);
      }
    });
  },

  initDocumentationCharts: function() {
    /* ----------==========     Daily Sales Chart initialization For Documentation    ==========---------- */

    

    /* ----------==========     Daily Sales Chart initialization    ==========---------- */

  // dataDailySalesChart = {
  //   series: [
  //   [12, 17, 7, 17, 23, 18, 38]
  //   ]
  // };

  // optionsDailySalesChart = {
  //   lineSmooth: Chartist.Interpolation.cardinal({
  //     tension: 0
  //   }),
  //   low: 0,
  //   high: 50,
  //   chartPadding: {
  //     top: 0,
  //     right: 0,
  //     bottom: 0,
  //     left: 0
  //   },
  // }

  // var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

  // md.startAnimationForLineChart(dailySalesChart);



  /* ----------==========     Emails Subscription Chart initialization    ==========---------- */


}
}

$(document).ready(function() {
  dataDailySalesChart = {
    labels: [1,2,3,4,5,6,7],
    series: [
    [12, 17, 7, 17, 23, 18, 38]
    ]
  };

  optionsDailySalesChart = {
    lineSmooth: Chartist.Interpolation.cardinal({
      tension: 0
    }),
    low: 0,
    chartPadding: {
      top: 0,
      right: 0,
      bottom: 0,
      left: 0
    },
  }

  var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

  $(document).ready(function() {
    dataDailySalesChart = {
      labels: [1,2,3,4,5,6,7],
      series: [
      [12, 17, 7, 17, 23, 18, 38]
      ]
    };

    optionsDailySalesChart = {
      lineSmooth: Chartist.Interpolation.cardinal({
        tension: 0
      }),
      low: 0,
      chartPadding: {
        top: 0,
        right: 0,
        bottom: 0,
        left: 0
      },
    }

    var dailySalesChart2 = new Chartist.Line('#dailySalesChart2', dataDailySalesChart, optionsDailySalesChart);

    md.startAnimationForLineChart(dailySalesChart);
    setInterval(function() {
      $.get('getEmbeddedData.php', function(data) {
        data = JSON.parse(data)
        var data1counter = 1;
        data1 = {
          labels: data.map(function(x){ return data1counter++}),
          series: [
          data.map(function(x){ return parseInt(x["Steps"])})
          ]
        };
        dailySalesChart.update(data1)

        var data2counter = 1;
        data2 = {
          labels: data.map(function(x){ return data2counter++}),
          series: [
          data.map(function(x){ return parseInt(x["Temperature"])})
          ]
        };
        dailySalesChart2.update(data2)
      })
    }, 2000)
  });
})