<%@ include file="/WEB-INF/views/_pagedirectives.jspf" %>
<%@ page contentType="text/javascript" %>
(function() {
  const $this = $('#thing_${id}');
  $this.fadeOut(300, () => { $this.remove() });
  $('#notifier-body').text('Thing removed successfully');
  $('#notifier').toast('show');
})()