$('#modalClientProfile').on('show.bs.modal', function (e) {
    var firstName = e.relatedTarget.dataset.firstname;
    var lastName = e.relatedTarget.dataset.lastname;
    var imageName = e.relatedTarget.dataset.imagename;
    var phone = e.relatedTarget.dataset.phone;
    var role = e.relatedTarget.dataset.role;
    var email = e.relatedTarget.dataset.email;
    var userId = e.relatedTarget.dataset.userid;
    var modal = $(this);
    modal.find('#roleInput').text(role);
    modal.find('#imagePreview').css("background-image", "url('" + imageName + "')");
    modal.find('#nameText').text(firstName + ' ' + lastName);
    modal.find('#emailText').text(email);
    modal.find('#phoneText').text(phone);
    modal.find('#roleText').text(role);
    modal.find('#clientId').val(userId);
    var moneyBalance = e.relatedTarget.dataset.moneybalance;
    var personalDiscount = e.relatedTarget.dataset.personaldiscount;
    var boughtTrainings = e.relatedTarget.dataset.boughttrainings;
    modal.find('#moneyText').text(moneyBalance);
    modal.find('#discountInput').val(personalDiscount);
    modal.find('#boughtText').text(boughtTrainings);
});

$('#modalTrainerProfile').on('show.bs.modal', function (e) {
    var firstName = e.relatedTarget.dataset.firstname;
    var lastName = e.relatedTarget.dataset.lastname;
    var imageName = e.relatedTarget.dataset.imagename;
    var phone = e.relatedTarget.dataset.phone;
    var role = e.relatedTarget.dataset.role;
    var email = e.relatedTarget.dataset.email;
    var modal = $(this);
    modal.find('#roleInput').text(role);
    modal.find('#imagePreview').css("background-image", "url('" + imageName + "')");
    modal.find('#nameText').text(firstName + ' ' + lastName);
    modal.find('#emailText').text(email);
    modal.find('#phoneText').text(phone);
    modal.find('#roleText').text(role);
    var institution = e.relatedTarget.dataset.institution;
    var graduationYear = e.relatedTarget.dataset.graduationyear;
    var instagramLink = e.relatedTarget.dataset.instagramlink;
    var rating = e.relatedTarget.dataset.rating;
    modal.find('#institutionText').text(institution);
    modal.find('#graduationText').text(graduationYear);
    modal.find('#instagramRef').attr('href',instagramLink);
    $('#ratingTrainerProfile').raty({
        readOnly: true,
        score: rating,
        half: true
    });
});

$('#modalFeedbackReply').on('show.bs.modal', function (e) {
    var replyEmail = e.relatedTarget.dataset.replyemail;
    var replySubject = e.relatedTarget.dataset.replysubject;
    var feedbackId = e.relatedTarget.dataset.feedbackid;
    var modal = $(this);
    modal.find('#feedbackId').val(feedbackId);
    modal.find('#replyEmail').val(replyEmail);
    modal.find('#replySubject').val(replySubject);
});

$('#modalEditTraining').on('show.bs.modal', function (e) {
    var trainingId = e.relatedTarget.dataset.trainingid;
    var trainerName = e.relatedTarget.dataset.trainername;
    var trainingDate = e.relatedTarget.dataset.trainingdate;
    var trainingTime = e.relatedTarget.dataset.trainingtime;
    var trainingDescription = e.relatedTarget.dataset.trainingdescription;
    var modal = $(this);
    modal.find('#editTrainingId').val(trainingId);
    modal.find('#editTrainerName').text(trainerName);
    modal.find('#editTrainingDate').val(trainingDate);
    modal.find('#editTrainingTime').val(trainingTime);
    modal.find('#editTrainingDescription').val(trainingDescription);
    setEditRanges();
});

$('#modalRating').on('show.bs.modal', function (e) {
    var trainingId = e.relatedTarget.dataset.trainingid;
    var trainerId = e.relatedTarget.dataset.trainerid;
    var modal = $(this);
    modal.find('#trainingId').val(trainingId);
    modal.find('#trainerId').val(trainerId);
});

$('#modalDescription').on('show.bs.modal', function (e) {
    var trainingId = e.relatedTarget.dataset.trainingid;
    var trainingDescription = e.relatedTarget.dataset.trainingdescription;
    var modal = $(this);
    modal.find('#descrTrainingId').val(trainingId);
    modal.find('#trainingDescription').val(trainingDescription);
});

$('#modalAddTraining').on('show.bs.modal', function (e) {
    setAddRanges();
});

function setAddRanges() {
    $('#addTrainingDate').attr('min', currentDate());
    $('#addTrainingTime').attr('min', '07:00');
    $('#addTrainingTime').attr('max', '23:00');
}

function setEditRanges() {
    $('#editTrainingDate').attr('min', currentDate());
    $('#editTrainingTime').attr('min', '07:00');
    $('#editTrainingTime').attr('max', '23:00');
}

function currentDate() {
    var dtToday = new Date();
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    var minDate= year + '-' + month + '-' + day;
    return minDate;
}